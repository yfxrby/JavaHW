package ru.ifmo.rain.nachkin.student;

import info.kgeorgiy.java.advanced.student.Student;
import info.kgeorgiy.java.advanced.student.StudentQuery;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

public class StudentDB implements StudentQuery {

    @Override
    public List<String> getFirstNames(List<Student> students) {
        return students.stream().map(Student::getFirstName).collect(Collectors.toList());
    }

    @Override
    public List<String> getLastNames(List<Student> students) {
        return students.stream().map(Student::getLastName).collect(Collectors.toList());
    }

    @Override
    public List<String> getGroups(List<Student> students) {
        return students.stream().map(Student::getGroup).collect(Collectors.toList());
    }

    @Override
    public List<String> getFullNames(List<Student> students) {
        return students.stream().map(student -> student.getFirstName() + " " + student.getLastName()).collect(Collectors.toList());
    }

    @Override
    public Set<String> getDistinctFirstNames(List<Student> students) {
        return getFirstNames(students).stream().sorted().collect(Collectors.toCollection(TreeSet::new));
    }

    @Override
    public String getMinStudentFirstName(List<Student> students) {
        return students.stream().min(Student::compareTo).map(Student::getFirstName).orElse("");
    }

    @Override
    public List<Student> sortStudentsById(Collection<Student> students) {
        return students.stream().sorted(Student::compareTo).collect(Collectors.toList());
    }

    public List<Student> sortStudentsByName(Collection<Student> students) {
        return students.stream().sorted(Comparator.comparing(Student::getLastName).thenComparing(Student::getFirstName).thenComparing(Student::getId)).collect(Collectors.toList());
    }

    @Override
    public List<Student> findStudentsByFirstName(Collection<Student> students, String name) {
        return sortStudentsByName(students.stream().filter(student -> student.getFirstName().equals(name)).collect(Collectors.toList()));
    }

    @Override
    public List<Student> findStudentsByLastName(Collection<Student> students, String name) {
        return sortStudentsByName(students.stream().filter(student -> student.getLastName().equals(name)).collect(Collectors.toList()));
    }

    @Override
    public List<Student> findStudentsByGroup(Collection<Student> students, String group) {
        return sortStudentsByName(students.stream().filter(student -> student.getGroup().equals(group)).collect(Collectors.toList()));
    }

    @Override
    public Map<String, String> findStudentNamesByGroup(Collection<Student> students, String group) {
        return students.stream().filter(student -> student.getGroup().equals(group)).collect(Collectors.toMap(Student::getLastName, Student::getFirstName, BinaryOperator.minBy(String::compareTo)));
    }
}
