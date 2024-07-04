package com.model;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

public class HibernateCriteriaExamples {
	@SuppressWarnings("unchecked")
	public static void main(String[]args) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.beginTransaction();
		Criteria criteria = session.createCriteria(Employee.class);
		List<Employee> empList = criteria.list();
		for(Employee emp: empList) {
			System.out.println("ID="+emp.getId()+ emp.getName()+emp.getSalary());
		}
		criteria = session.createCriteria(Employee.class).add(Restrictions.eq("id", new Integer(3)));
		Employee emp= (Employee) criteria.uniqueResult();
		System.out.println("Name=" +emp.getName());
		
		//Pagination Example 
		empList = session.createCriteria(Employee.class).addOrder(Order.desc("id")).setFirstResult(0).setMaxResults(2).list();
		for(Employee emp4 : empList) {
			System.out.println("Paginated Employess:" + emp4.getId()+emp4.getName());
		}
		empList = session.createCriteria(Employee.class).add(Restrictions.like("name", "%m%")).list();
		for(Employee emp4 : empList) {
			System.out.println(" Employees having \"m\" in their name : "+emp4.getName());
		}
		
		//Projections Example
		Criteria c = session.createCriteria(Employee.class);
		c.setProjection(Projections.rowCount());
		empList = c.add(Restrictions.like("name", "%m%")).list();
		System.out.println("Number of Employees with 'm' in their name =" + empList.getFirst());
		
		// Using Projections for sum, min, max aggregate Functions
		Criteria d = session.createCriteria(Employee.class);
		empList = d.setProjection(Projections.sum("salary")).list();
		System.out.println("Sum of salaries =" + empList.getFirst());
		
		//Rollback transaction to avoid messing test data
		tr.commit();
		//closing hibernate resources
		sessionFactory.close();

		}

}
