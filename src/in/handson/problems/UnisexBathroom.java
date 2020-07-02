package in.handson.problems;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class UnisexBathroom {
    public static void main(String[] args) {
        Bathroom bathroom = new Bathroom(5);
        List<Person> users = new ArrayList<>();
        for (int i = 0; i < ((new Random()).nextInt(20)+10); i++) {
            if ((i % 2) == 0) {
                users.add(new Person(("Sam " + (i+1)), Gender.MEN, bathroom));
            } else {
                users.add(new Person(("Jasmine " + (i+1)), Gender.WOMEN, bathroom));
            }
        }

        users.stream().map(person -> new Thread(person, person.name)).forEach(thread -> thread.start());
    }
}

class Bathroom{
    int capacity;

    private Lock lock = new ReentrantLock();
    LinkedHashSet<Person> persons;
    private Gender gender;

    Bathroom(int capacity){
        this.capacity = capacity;
        persons = new LinkedHashSet<>();
        gender = Gender.NONE;
    }

    public void addPerson(Person person){
        this.lock.lock();
        try {
            if (this.isEmpty()) {
                this.gender = person.gender;
            }

            if (!this.isFull() && getCurrentGender().equals(person.gender)
                    && !this.persons.contains(person)) {
                if (this.persons.add(person)) {
                    System.out.println(person.name + " Entered Bathroom");
                }

                if (isFull()) {
                    System.out.println("Bathroom is Full");
                }
            }
        }finally {
            lock.unlock();
        }
    }

    public void removeUser(Person person){
        this.lock.lock();
        try {
            if (!this.isEmpty()) {
                if (this.persons.remove(person)) {
                    System.out.println(person.name + " left bathroom");
                }
                if (this.isEmpty()) {
                    System.out.println("Bathroom is empty");
                    gender = Gender.NONE;
                }
            }
        }finally {
            this.lock.unlock();
        }
    }

    Gender getCurrentGender(){
        return this.gender;
    }

    boolean isEmpty(){
        return this.persons.isEmpty();
    }

    boolean isFull(){
        return this.persons.size() == 5;
    }

    boolean isUserInBathroom(Person person){
        return this.persons.contains(person);
    }
}

class Person implements Runnable{
    String name;
    Gender gender;
    boolean needsBathroom;
    boolean canLeave;
    Bathroom bathroom;

    public Person(String name, Gender gender, Bathroom bathroom) {
        this.name = name;
        this.gender = gender;
        this.needsBathroom = true;
        this.canLeave = false;
        this.bathroom = bathroom;
    }

    void useBathroom() {
        this.bathroom.addPerson(this);
        if(this.bathroom.isUserInBathroom(this)){
            try {
                TimeUnit.SECONDS.sleep(1);
                this.canLeave = true;
                System.out.println(this.name + " Done");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    void leaveBathroom(){
        this.bathroom.removeUser(this);
        this.canLeave=false;
        this.needsBathroom=false;
    }

    @Override
    public void run() {
        System.out.println(name);
        while (this.needsBathroom){
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if((this.bathroom.getCurrentGender().equals(this.gender) ||
            this.bathroom.getCurrentGender().equals(Gender.NONE))
                    && !this.bathroom.isUserInBathroom(this)
            && !this.bathroom.isFull()){
                this.useBathroom();
            }

            if(this.canLeave){
                this.leaveBathroom();
            }
        }
    }
}

enum Gender{
    NONE,MEN,WOMEN;
}
