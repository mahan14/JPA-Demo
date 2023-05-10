package com.ridemo.hibernatefundamentals;

import com.ridemo.hibernatefundamentals.airport.Airport;
import com.ridemo.hibernatefundamentals.airport.Passenger;
import com.ridemo.hibernatefundamentals.airport.Ticket;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernatefundamentals.m02.ex01");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        Airport airport = new Airport(1, "Henri Coanda");

        Passenger john = new Passenger(1, "John Smith");
        john.setAirport(airport);
        Passenger mike = new Passenger(2, "Michael Johnson");
        mike.setAirport(airport);

        airport.addPassenger(john);
        airport.addPassenger(mike);

        Ticket ticket1 = new Ticket(1, "AA1234");
        ticket1.setPassenger(john);

        Ticket ticket2 = new Ticket(2, "BB5678");
        ticket2.setPassenger(john);

        john.addTicket(ticket1);
        john.addTicket(ticket2);

        Ticket ticket3 = new Ticket(3, "CC0987");
        ticket3.setPassenger(mike);
        mike.addTicket(ticket3);

        em.persist(airport);
        em.persist(john);
        em.persist(mike);

        em.persist(ticket1);
        em.persist(ticket2);
        em.persist(ticket3);

        EntityTransaction et = em.getTransaction();
                et.commit();
        Passenger pa=  em.find(Passenger.class,1);
        System.out.println(pa.getTickets().get(0).getNumber());
        System.out.println(pa.getTickets().get(0).getId());

        emf.close();

    }
}
/*

Student Courses Batches

Student
SID Name  Courses Batch

Course
CourseID CourseDetail StudentsEnrolled Batchs
Spring boot

Batches
BatchID Course Students



Course c1 = new Course();
c1.setCourseId(123);
c1.setCourseName(Javawebfundamentals);

Student st1= new Studnet();
st1.setCourse(c1);

//Find out the course having 2 batches and less than 3 studensts;


*/
