package Revature.Employee;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Path("/controller")
public class MyController {
	private static final Logger logger = LogManager.getLogger(MyController.class);
	private Employees x;
	public static int state;
	@SuppressWarnings("unused")
	private Transaction transaction;

	
	@POST
	@Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	    public static String getHelloParam(String data) throws ClassNotFoundException, JsonProcessingException {
	        Session session = null;
	        Transaction transaction = null;
	        ObjectMapper obj=new ObjectMapper();
	        System.out.println(data);
	        user u=obj.readValue(data, user.class);
	        System.out.println(u);
	        int uid=u.getUid();
	        state=uid;
	        String pwd=u.getPwd();
	        String fn = null,un = null,des = null; 
	        login l=new login();
	        try {
	            session = HibernateUtils.getSessionFactory().openSession();
	            transaction = session.beginTransaction();
	            Employees e = (Employees) session.createQuery("FROM Employees E WHERE E.id = :userId")
	                    .setParameter("userId", uid).uniqueResult();
	            fn=e.getFirstName();
	            un=e.getLastName();
	            des=e.getDesignation();
	            if (e != null && e.getPassword().equals(pwd)) {
	                l.setId(uid);
	                l.setValue("true");
	                l.setDes(e.getDesignation());
	                System.out.println(l);
	                logger.info(e.getFirstName()+" "+e.getLastName()+" (Designation : "+e.getDesignation()+") has signed into the portal");
	                return obj.writeValueAsString(l);
	            }
	            transaction.commit();
	        } catch (Exception ex) {
	            if (transaction != null) {
	                transaction.rollback();
	            }
	            ex.printStackTrace();
	        }
	        l.setId(uid);
            l.setValue("false");
            l.setDes("null");
            logger.info(fn+" "+un+" (Designation : "+des+") has entered the wrong password and tried to sign into the portal");
            return obj.writeValueAsString(l);
	}
	
	
	@PUT
	@Path("/changePassword")
    @Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	    public String changePassword(String data) throws ClassNotFoundException, JsonProcessingException {
	        Session session = null;
	        Transaction transaction = null;
	        ObjectMapper mp=new ObjectMapper();
	        login g=new login();
	        user u=mp.readValue(data, user.class);
	        u.setUid(state);
	        try {
	            session = HibernateUtils.getSessionFactory().openSession();
	            transaction = session.beginTransaction();
	            Employees e = (Employees) session.createQuery("FROM Employees E WHERE E.id = :userId")
	                    .setParameter("userId", u.getUid()).uniqueResult();
	            if (e != null && e.getPassword().equals(u.getPwd())) {
	            Query q=session.createQuery("update Employees set password=:n  where eid=:i");  
		        q.setParameter("n",u.getNpwd());  
		        q.setParameter("i",state);  
		        q.executeUpdate();       
                transaction.commit();
                g.setValue("true");
                logger.info(e.getFirstName()+" "+e.getLastName()+" (Designation : "+e.getDesignation()+") has changed his password ");
                return mp.writeValueAsString(g);
	            }
	            else {
	            	g.setValue("false");
		            return mp.writeValueAsString(g);
	            }
	            } 
	            catch (Exception e) {
	            if (transaction != null) {
	                transaction.rollback();
	            }
	            e.printStackTrace();
	            g.setValue("false");
	            return mp.writeValueAsString(g);
	        }
	        
	    }
	
	
	
	@POST
	@Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	    public String getSubmit(String data) throws ClassNotFoundException, JsonProcessingException {
	        Session session = null;
	        Transaction transaction = null;
	        Date d=new Date();
	        ObjectMapper mp=new ObjectMapper();
	        submit s=mp.readValue(data, submit.class);
	        System.out.println(s);
	        submitSuccess a=new submitSuccess();
	        try {
	            session = HibernateUtils.getSessionFactory().openSession();
	            transaction = session.beginTransaction();
	            System.out.println(state);
	            x = session.get(Employees.class, state);
	            System.out.println(x);
	            ERSRequest r=new ERSRequest(x.getFirstName(),x.getLastName(),s.getsDate(),s.geteDate(),d,s.getAt(),s.getRt(),"pending",s.getPara(),x);
	            session.save(r);
	            transaction.commit();
	            a.setSts(1);
	            logger.info(x.getFirstName()+" "+x.getLastName()+" (Designation : "+x.getDesignation()+") has submitted a ers request into the portal");
	            return mp.writeValueAsString(a);
	            } catch (Exception e) {
	            if (transaction != null) {
	                transaction.rollback();
	            }
	            e.printStackTrace();
	            a.setSts(0);
	            return mp.writeValueAsString(a);
	        }
	        
	    }
	@POST
	@Path("/addEmployee")
    @Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	    public String addEmployee(String data) throws ClassNotFoundException, JsonProcessingException {
	        Session session = null;
	        Transaction transaction = null;
	        ObjectMapper mp=new ObjectMapper();
	        Employees s=mp.readValue(data, Employees.class);
	        System.out.println(s);
	        submitSuccess a=new submitSuccess();
	        try {
	            session = HibernateUtils.getSessionFactory().openSession();
	            transaction = session.beginTransaction();
	            s.setPassword(s.getFirstName()+"123");
	            session.save(s);
	            transaction.commit();
	            a.setSts(1);
	            logger.info(s.getFirstName()+" "+s.getLastName()+" (Designation : "+s.getDesignation()+") is a new employee");
	            return mp.writeValueAsString(a);
	            } catch (Exception e) {
	            if (transaction != null) {
	                transaction.rollback();
	            }
	            e.printStackTrace();
	            a.setSts(0);
	            return mp.writeValueAsString(a);
	        }
	        
	    }

	@GET
	@Path("/profile")
    @Produces(MediaType.TEXT_PLAIN)
	 public String getProfile() throws ClassNotFoundException, JsonProcessingException {
	   Session session = null;
       transaction = null;
       ObjectMapper mp = new ObjectMapper();
       try {
              session = HibernateUtils.getSessionFactory().openSession();
              transaction = session.beginTransaction();
              Employees e=session.get(Employees.class, state);
              System.out.println(e);
              logger.info(e.getFirstName()+" "+e.getLastName()+" (Designation : "+e.getDesignation()+") viewed his / her profile");
	           
              return  mp.writeValueAsString(e);
    } catch (Exception ex) {
        ex.getMessage();
    }
    return null;
	}
	
	
	@GET
	@Path("/pending")
    @Produces(MediaType.TEXT_PLAIN)
	 public String getPending() throws ClassNotFoundException, JsonProcessingException {
	   Session session = null;
       transaction = null;
       ObjectMapper mp = new ObjectMapper();
       try {
              session = HibernateUtils.getSessionFactory().openSession();
              transaction = session.beginTransaction();
              @SuppressWarnings("unchecked")
			  List<ERSRequest> ers=session.createQuery("from ERSRequest where status='pending' and eid="+state).list();
              List<Pending> pend=new ArrayList<Pending>();
              for(ERSRequest e:ers) {
              Pending p=new Pending(e.getrId(),e.geteFname(),e.geteLname(),e.getStartDate(),e.getEndDate(),e.getReqDate(),e.getAmount(),e.getReqType(),e.getStatus(),e.getDescription(),e.getEmp().getId());
              pend.add(p);
              logger.info("user is : "+state+" has viewed the pending requests");
	           
              System.out.println(p);
           }
           return  mp.writeValueAsString(pend);
    } catch (Exception ex) {
        ex.getMessage();
    }
    return mp.writeValueAsString(null);
    }
	
	
	
	@GET
	@Path("/resolved")
    @Produces(MediaType.TEXT_PLAIN)
	 public String getResolved() throws ClassNotFoundException, JsonProcessingException {
	   Session session = null;
       transaction = null;
       ObjectMapper mp = new ObjectMapper();
       try {
              session = HibernateUtils.getSessionFactory().openSession();
              transaction = session.beginTransaction();
              @SuppressWarnings("unchecked")
			  List<ERSRequest> ers=session.createQuery("from ERSRequest where status='resolved' and eid="+state).list();
              List<Pending> pend=new ArrayList<Pending>();
              for(ERSRequest e:ers) {
              Pending p=new Pending(e.getrId(),e.geteFname(),e.geteLname(),e.getStartDate(),e.getEndDate(),e.getReqDate(),e.getAmount(),e.getReqType(),e.getStatus(),e.getDescription(),e.getEmp().getId());
              pend.add(p);
              System.out.println(p);
              logger.info("user is : "+state+" has viewed the resolved requests");
              }
              return  mp.writeValueAsString(pend);
    } catch (Exception ex) {
        ex.getMessage();
    }
    return null;
	}
	
	@GET
	@Path("/rejected")
    @Produces(MediaType.TEXT_PLAIN)
	 public String getRejected() throws ClassNotFoundException, JsonProcessingException {
	   Session session = null;
       transaction = null;
       ObjectMapper mp = new ObjectMapper();
       try {
              session = HibernateUtils.getSessionFactory().openSession();
              transaction = session.beginTransaction();
              @SuppressWarnings("unchecked")
			  List<ERSRequest> ers=session.createQuery("from ERSRequest where status='rejected' and eid="+state).list();
              List<Pending> pend=new ArrayList<Pending>();
              for(ERSRequest e:ers) {
              Pending p=new Pending(e.getrId(),e.geteFname(),e.geteLname(),e.getStartDate(),e.getEndDate(),e.getReqDate(),e.getAmount(),e.getReqType(),e.getStatus(),e.getDescription(),e.getEmp().getId());
              pend.add(p);
              System.out.println(p);
              logger.info("user is : "+state+" has viewed the rejected requests");
              }
              return  mp.writeValueAsString(pend);
    } catch (Exception ex) {
        ex.getMessage();
    }
    return null;
	}
	
	
	
	@PUT
	@Path("/updateProfile")
    @Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	    public String update(String data) throws ClassNotFoundException, JsonProcessingException {
	        Session session = null;
	        Transaction transaction = null;
	        login g=new login();
	        ObjectMapper mp=new ObjectMapper();
	        update u=mp.readValue(data, update.class);
	        System.out.println(u);
	        try {
	            session = HibernateUtils.getSessionFactory().openSession();
	            transaction = session.beginTransaction();
	            System.out.println(state);
	            if(!u.getLname().equals("")) {
	            	Query q=session.createQuery("update Employees set lastName=:n  where id=:i");  
		            q.setParameter("n",u.getLname());  
		            q.setParameter("i",state);  
		            q.executeUpdate();  
		            
	            }
	            if(!u.getEmail().equals("")) {
	            	Query q=session.createQuery("update Employees set emailid=:n  where id=:i");  
		            q.setParameter("n",u.getEmail());  
		            q.setParameter("i",state);  
		            q.executeUpdate();  
		            
	            }
	            if(!u.getDoor().equals("")) {
	            	Query q=session.createQuery("update Employees set address.doornumber=:n  where id=:i");  
		            q.setParameter("n",u.getDoor());  
		            q.setParameter("i",state);  
		            q.executeUpdate();  
		           
	            }
	            if(!u.getSt().equals("")) {
	            	Query q=session.createQuery("update Employees set address.street=:n  where id=:i");  
		            q.setParameter("n",u.getSt());  
		            q.setParameter("i",state);  
		            q.executeUpdate();  
		            
	            }
	            if(!u.getDst().equals("")) {
	            	Query q=session.createQuery("update Employees set address.district=:n  where id=:i");  
		            q.setParameter("n",u.getDst());  
		            q.setParameter("i",state);  
		            q.executeUpdate();  
		            
	            }
	            if(u.getPin()!=0) {
	            	Query q=session.createQuery("update Employees set address.pincode=:n  where id=:i");  
		            q.setParameter("n",u.getPin());  
		            q.setParameter("i",state);  
		            q.executeUpdate();   
	            }
	           if(u.getPhn()!=0) {
	            	Query q=session.createQuery("update Employees set contactno=:n  where id=:i");  
		            q.setParameter("n",u.getPhn());  
		            q.setParameter("i",state);  
		            q.executeUpdate();   
	            }
	            transaction.commit();
	            
	            g.setValue("true");
	            return mp.writeValueAsString(g);
	            } catch (Exception e) {
	            if (transaction != null) {
	                transaction.rollback();
	            }
	            e.printStackTrace();
	            g.setValue("false");
	            return mp.writeValueAsString(g);
	        }
	        
	    }
	
	@PUT
	@Path("/updateRequest")
    @Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	    public String acceptRequest(String data) throws ClassNotFoundException, JsonProcessingException {
	        Session session = null;
	        Transaction transaction = null;
	        login g=new login();
	        ObjectMapper mp=new ObjectMapper();
	        accept u=mp.readValue(data, accept.class);
	        System.out.println(u);
	        try {
	            session = HibernateUtils.getSessionFactory().openSession();
	            transaction = session.beginTransaction();
	            Query q=session.createQuery("update ERSRequest set status=:n  where rId=:i");  
		        q.setParameter("n","resolved");  
		        q.setParameter("i",u.getR());  
		        q.executeUpdate();       
                transaction.commit();
	            g.setValue("true");
	            logger.info("user is : "+state+" has resolved the request of user id"+u.getE()+" with request id "+u.getR() );
		           
	            return mp.writeValueAsString(g);
	            } catch (Exception e) {
	            if (transaction != null) {
	                transaction.rollback();
	            }
	            e.printStackTrace();
	            g.setValue("false");
	            return mp.writeValueAsString(g);
	        }
	        
	    }
	
	@PUT
	@Path("/rejectRequest")
    @Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	    public String rejectRequest(String data) throws ClassNotFoundException, JsonProcessingException {
	        Session session = null;
	        Transaction transaction = null;
	        login g=new login();
	        ObjectMapper mp=new ObjectMapper();
	        accept u=mp.readValue(data, accept.class);
	        System.out.println(u);
	        try {
	            session = HibernateUtils.getSessionFactory().openSession();
	            transaction = session.beginTransaction();
	            Query q=session.createQuery("update ERSRequest set status=:n  where rId=:i");  
		        q.setParameter("n","rejected");  
		        q.setParameter("i",u.getR());  
		        q.executeUpdate();       
                transaction.commit();
	            g.setValue("true");
	            logger.info("user is : "+state+" has rejetced the request of user id"+u.getE()+" with request id "+u.getR() );
	            return mp.writeValueAsString(g);
	            } catch (Exception e) {
	            if (transaction != null) {
	                transaction.rollback();
	            }
	            e.printStackTrace();
	            g.setValue("false");
	            return mp.writeValueAsString(g);
	        }
	        
	    }
	
	
	
	@GET
	@Path("/AllPending")
    @Produces(MediaType.TEXT_PLAIN)
	 public String getPendingAll() throws ClassNotFoundException, JsonProcessingException {
	   Session session = null;
       transaction = null;
       ObjectMapper mp = new ObjectMapper();
       try {
              session = HibernateUtils.getSessionFactory().openSession();
              transaction = session.beginTransaction();
              @SuppressWarnings("unchecked")
			  List<ERSRequest> ers=session.createQuery("from ERSRequest where status='pending'").list();
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
    return  mp.writeValueAsString(null);

    }
	
	
	@GET
	@Path("/AllResolved")
    @Produces(MediaType.TEXT_PLAIN)
	 public String getAllResolved() throws ClassNotFoundException, JsonProcessingException {
	   Session session = null;
       transaction = null;
       ObjectMapper mp = new ObjectMapper();
       try {
              session = HibernateUtils.getSessionFactory().openSession();
              transaction = session.beginTransaction();
              @SuppressWarnings("unchecked")
			  List<ERSRequest> ers=session.createQuery("from ERSRequest where status='resolved'").list();
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
	
	@GET
	@Path("/AllRejected")
    @Produces(MediaType.TEXT_PLAIN)
	 public String getAllRejected() throws ClassNotFoundException, JsonProcessingException {
	   Session session = null;
       transaction = null;
       ObjectMapper mp = new ObjectMapper();
       try {
              session = HibernateUtils.getSessionFactory().openSession();
              transaction = session.beginTransaction();
              @SuppressWarnings("unchecked")
			  List<ERSRequest> ers=session.createQuery("from ERSRequest where status='rejected'").list();
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
	
	
	
	
	@POST
	@Path("/EmployeeProfile")
    @Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	    public String getEmployee(String data) throws ClassNotFoundException, JsonProcessingException {
	        Session session = null;
	        Transaction transaction = null;
	        System.out.println(data);
	        ObjectMapper mp=new ObjectMapper();
	        login l=mp.readValue(data,login.class);
	        System.out.println(l);
	        try {
	            session = HibernateUtils.getSessionFactory().openSession();
	            transaction = session.beginTransaction();
	            x = session.get(Employees.class,l.getId());
	            System.out.println(x);
	            transaction.commit();
	            return mp.writeValueAsString(x);
	            } catch (Exception e) {
	            if (transaction != null) {
	                transaction.rollback();
	            }
	            return mp.writeValueAsString(null);
	        }
	        
	    }
	
	@GET
	@Path("/EmployeeProfileAll")
    @Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	    public String getEmployeeAll() throws ClassNotFoundException, JsonProcessingException {
	        Session session = null;
	        Transaction transaction = null;
	        ObjectMapper mp=new ObjectMapper();
	        try {
	            session = HibernateUtils.getSessionFactory().openSession();
	            transaction = session.beginTransaction();
	            @SuppressWarnings("unchecked")
				List<Employees> emp=session.createQuery("from Employees").list();
	            transaction.commit();
	            return mp.writeValueAsString(emp);
	            } catch (Exception e) {
	            if (transaction != null) {
	                transaction.rollback();
	            }
	            return mp.writeValueAsString(null);
	        }
	        
	    }
	
	
	  @GET
	  @Path("/logout")
      @Produces(MediaType.TEXT_PLAIN)
	  public String clear() throws ClassNotFoundException, JsonProcessingException {
        ObjectMapper mp = new ObjectMapper();
        state=0;
        System.out.println(state);
        login g=new login();
        g.setValue("true");
        logger.info("user is : "+state+" has logged out of the portal");
        return  mp.writeValueAsString(g);
	}
}
