package com.tedneward.example;

import java.beans.*;
import java.util.*;

public class Person implements Comparable<Person>{
  private int age;
  private String name;
  private double salary;
  private String ssn;
  private boolean propertyChangeFired = false;
  
  public Person() {
    this("", 0, 0.0d);
  }
  
  public Person(String n, int a, double s) {
    name = n;
    age = a;
    salary = s;
    ssn = "";
  }

  public int getAge() {
    return age;
  }
  public void setAge (int age) {
    if(age < 0) {
      throw new IllegalArgumentException("Age is less than zero");
    }
    this.age = age;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    if(name == null) {
      throw new IllegalArgumentException("Name is null");
    }
    this.name = name;
  }

  public double getSalary() {
    return salary;
  }
  public void setSalary(double salary) {
    this.salary = salary;
  }
  
  public String getSSN() {
    return ssn;
  }
  public void setSSN(String value) {
    String old = ssn;
    ssn = value;
    
    this.pcs.firePropertyChange("ssn", old, value);
    propertyChangeFired = true;
  }
  public boolean getPropertyChangeFired() {
    return propertyChangeFired;
  }

  public double calculateBonus() {
    return salary * 1.10;
  }
  
  public String becomeJudge() {
    return "The Honorable " + name;
  }
  
  public int timeWarp() {
    return age + 10;
  }


  public boolean equals(Object o) {
    if(o instanceof Person) {
      return this.name.equals(((Person) o).getName()) && this.age == ((Person) o).getAge();
    } else {
      return false;
    }
  }

  public String toString() {
    return "[Person name:" + this.name + " age:" + this.age + " salary:" + this.salary + "]";
  }

  public static ArrayList<Person> getNewardFamily() {
    ArrayList<Person> list = new ArrayList<Person>();
    list.add(new Person("Ted", 41, 250000));
    list.add(new Person("Charlotte", 43, 150000));
    list.add(new Person("Michael", 22, 10000));
    list.add(new Person("Matthew", 15, 0));
    return list;
  }

  static class AgeComparator implements Comparator<Person> {
    @Override
    public int compare(Person p1, Person p2) {
      int difference = p1.getAge() - p2.getAge();
      if(difference > 0) {
        return 1;
      } else if(difference < 0) {
        return -1;
      } else {
        return 0;
      }
    }
  }

  @Override
  public int compareTo(Person other) {
    double difference = this.salary - other.getSalary();
    if(difference > 0) {
      return -1;
    } else if(difference < 0) {
      return 1;
    } else {
      return 0;
    }
  }

  // PropertyChangeListener support; you shouldn't need to change any of
  // these two methods or the field
  //
  private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
  public void addPropertyChangeListener(PropertyChangeListener listener) {
      this.pcs.addPropertyChangeListener(listener);
  }
  public void removePropertyChangeListener(PropertyChangeListener listener) {
      this.pcs.removePropertyChangeListener(listener);
  }


}
