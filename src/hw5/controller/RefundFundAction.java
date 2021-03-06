package hw5.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.formbeanfactory.FormBeanFactory;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;

import hw5.controller.Action;
import hw5.databean.CustomerCredential;
import hw5.databean.CustomerFunds;
import hw5.databean.CustomerInfo;
import hw5.databean.Funds;
import hw5.formbean.IdForm;
import hw5.model.CustomerFundsDAO;
import hw5.model.CustomerInfoDAO;
import hw5.model.FundsDAO;
import hw5.model.Model;

public class RefundFundAction extends Action {
    private FormBeanFactory<IdForm> formBeanFactory = new FormBeanFactory<>(IdForm.class);

    private CustomerFundsDAO customerFundsDAO;
    private CustomerInfoDAO customerDAO;

    public RefundFundAction(Model model) {
    	customerFundsDAO = model.getCustomerFundsDAO();
    	customerDAO = model.getCustomerInfoDAO();
    }

    public String getName() {
        return "refund.do";
    }
    public String performGet(HttpServletRequest request) {
        return performPost(request);
    }
    public String performPost(HttpServletRequest request) {
        IdForm form = formBeanFactory.create(request);

        if (form.hasValidationErrors()) {
            return "error.jsp";
        }

        try {

        	HttpSession session = request.getSession();
            CustomerCredential customer = (CustomerCredential) session.getAttribute("customer");

            CustomerInfo[] customer_info = customerDAO.match(MatchArg.equals("customer_email", customer.getCustomer_email()));
            CustomerInfo customerInfo = customerDAO.read(customer.getCustomer_email());
            CustomerFunds[] customer_funds = customerFundsDAO.match(MatchArg.equals("customer_email", customer.getCustomer_email()));
            CustomerFunds customer_balance = customerFundsDAO.read(form.getIdAsInt());
        	
            System.out.println("Customer Balance "+customer_info[0].getBalance());
            System.out.println("Get ID "+form.getId());
//            int new_balance = (Integer.parseInt(customer_info[0].getBalance()) + Integer.parseIntrefresh_balance[0].getTotal_value());
            
            customerFundsDAO.delete(form.getId());
            customerInfo.setBalance(customer_balance.getTotal_value() + customerInfo.getBalance());
            customerDAO.update(customerInfo);
            request.setAttribute("customer_info", customer_info[0]);
            request.setAttribute("customer_funds", customer_funds);
            return ("welcome.jsp");

        } catch (RollbackException e) {
            request.setAttribute("error", e.getMessage());
            return "error.jsp";
        	}
    	}
    }

//CustomerFunds fund = customerFundsDao.read(form.getIdAsInteger());
//CustomerInfo customerInfo = customerInfoDao.read(currentUser.getCustomerEmail());
//customerFundsDao.delete(form.getIdAsInteger());
//customerInfo.setBalance(fund.getTotalValue() + customerInfo.getBalance());
//customerInfoDao.update(customerInfo);
//
//request.setAttribute("success_message", "Fund refunded successfully!");
//return Pages.CUSTOMER_VIEW_ACCOUNT;
//} catch (RollbackException ex) {
//request.setAttribute("error", ex.getMessage());
//return Pages.CUSTOMER_VIEW_ACCOUNT;
//}



