package kz.zaletov.springLessons.dao;

import kz.zaletov.springLessons.models.Person;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDao {
    private static int PEOPLE_COUNT;
    private List<Person> people;
    {
        people =  new ArrayList<>();
        people.add(new Person(++PEOPLE_COUNT, "Tom"));
        people.add(new Person(++PEOPLE_COUNT, "John"));
        people.add(new Person(++PEOPLE_COUNT,"Kate"));
        people.add(new Person(++PEOPLE_COUNT, "Ann"));
    }
    public List<Person> index(){
        return people;
    }
    public Person show(int id){
        return people.stream().filter(person -> person.getId()==id).findAny().orElse(null);
    }
    public void save(Person person){
        person.setId(++PEOPLE_COUNT);
        people.add(person);
    }
    public void update(int id, Person updatedPerson){
        Person p = people.stream().filter(person -> person.getId()==id).findAny().orElse(null);
        p.setName(updatedPerson.getName());
    }
    public void delete(int id){
        people.removeIf(p->p.getId()==id);
    }
}
