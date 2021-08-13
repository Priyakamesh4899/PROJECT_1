package Revature.Employee;


import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Path("/resource")
public class MyResource {
	@SuppressWarnings("unused")
	private Transaction transaction;     
	@POST
	@Path("/pending")
    @Produces(MediaType.APPLICATION_JSON)
	 public String getPending(String data) throws ClassNotFoundException, JsonProcessingException {
	   Session session = null;
       transaction = null;
       ObjectMapper mp = new ObjectMapper();
       try {
              session = HibernateUtils.getSessionFactory().openSession();
              transaction = session.beginTransaction();
              user u=mp.readValue(data, user.class);
              if(u.getUid()==0) {
            	  submitSuccess s=new submitSuccess();
            	    s.setSts(0);
            	    return mp.writeValueAsString(s);
              }
              else if(session.get(Employees.class,u.getUid())==null){
            	  submitSuccess s=new submitSuccess();
          	    s.setSts(2);
          	    return mp.writeValueAsString(s);
              }
			  @SuppressWarnings("unchecked")
			  List<ERSRequest> ers=session.createQuery("from ERSRequest where eid="+u.getUid()).list();
              List<Pending> pend=new ArrayList<Pending>();
              for(ERSRequest e:ers) {
              Pending p=new Pending(e.getrId(),e.geteFname(),e.geteLname(),e.getStartDate(),e.getEndDate(),e.getReqDate(),e.getAmount(),e.getReqType(),e.getStatus(),e.getDescription(),e.getEmp().getId());
              pend.add(p);
              System.out.println(p);
           }
           return  mp.writeValueAsString(pend);
    } catch (Exception ex) {
        ex.getMessage();
    }
    submitSuccess s=new submitSuccess();
    s.setSts(0);
    return mp.writeValueAsString(s);

}
	
	@GET
	@Path("/pendingAll")
    @Produces(MediaType.APPLICATION_JSON)
	 public String getPendingAll() throws ClassNotFoundException, JsonProcessingException {
		Session session = null;
       transaction = null;
       ObjectMapper mp = new ObjectMapper();
       try {
              session = HibernateUtils.getSessionFactory().openSession();
              transaction = session.beginTransaction();
			  @SuppressWarnings("unchecked")
			  List<ERSRequest> ers=session.createQuery("from ERSRequest").list();
              List<Pending> pend=new ArrayList<Pending>();
              for(ERSRequest e:ers) {
              Pending p=new Pending(e.getrId(),e.geteFname(),e.geteLname(),e.getStartDate(),e.getEndDate(),e.getReqDate(),e.getAmount(),e.getReqType(),e.getStatus(),e.getDescription(),e.getEmp().getId());
              pend.add(p);
              System.out.println(p);
           }
           return  mp.writeValueAsString(pend);
    } catch (Exception ex) {
        ex.getMessage();
    }
    return null;

}
}

