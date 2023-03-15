import com.emp.Employee;

import java.util.*;
import java.util.stream.Collectors;
import java.util.Map.Entry;
import java.util.Optional;

class EmployeeList {
    public static void main(String[] args) {

        List<Employee> employeeList = new ArrayList<Employee>();
        employeeList.add(new Employee(111, "Sneha", 33, "Female", "HR", 2011, 25000.0));
        employeeList.add(new Employee(123, "Gautam", 26, "Male", "Sales & Marketing", 2015, 13500.0));
        employeeList.add(new Employee(134, "Luthar", 30, "Male", "Infrastructure", 2012, 18000.0));
        employeeList.add(new Employee(145, "Murarry", 29, "Male", "Product Development", 2014, 32500.0));
        employeeList.add(new Employee(156, "Nima Singh", 28, "Female", "HR", 2013, 22700.0));
        employeeList.add(new Employee(167, "Farukhi", 44, "Male", "Security & Transport", 2016, 10500.0));
        employeeList.add(new Employee(178, "Shantanu Sharma", 36, "Male", "Account & Finance", 2010, 27000.0));
        employeeList.add(new Employee(189, "Lue", 32, "Male", "Product Development", 2015, 34500.0));
        employeeList.add(new Employee(199, "Camrun green", 25, "Female", "Sales & Marketing", 2016, 11500.0));
        employeeList.add(new Employee(201, "Darren", 38, "Male", "Security & Transport", 2015, 11000.5));
        employeeList.add(new Employee(212, "Jaslin Kaur", 28, "Female", "Infrastructure", 2014, 15700.0));
        employeeList.add(new Employee(223, "Vinay", 26, "Male", "Product Development", 2016, 28200.0));
        employeeList.add(new Employee(234, "Vasanthi Reddy", 28, "Female", "Account & Finance", 2013, 21300.0));
        employeeList.add(new Employee(245, "Donnie", 25, "Male", "Sales & Marketing", 2017, 10700.5));
        employeeList.add(new Employee(256, "Ali Z", 24, "Male", "Infrastructure", 2018, 12700.0));
        employeeList.add(new Employee(267, "Sanskriti Singh", 27, "Female", "Product Development", 2015, 28900.0));
        employeeList.add(new Employee(278, "AK Gupta", 32, "Male", "Product Development", 2012, 35700.0));

        //1.How many male and female employees are in the organization?
        Map<String, Long> numberOfMalaAndFemaleEmployee = employeeList
                .stream().collect(Collectors.groupingBy(Employee::getGender, Collectors.counting()));
        System.out.println(numberOfMalaAndFemaleEmployee);
        System.out.println("++++++++++++++++++++++++++++");

        //2.Print the name of every departments in the organization?
        employeeList.stream().map(Employee::getDepartment).distinct().forEach(System.out::println);

        //3.Average age of Male/Female employees?
        Map<String, Double> avgAgeOfMaleAndFemale = employeeList
                .stream().collect(Collectors.groupingBy(Employee::getGender, Collectors.averagingInt(Employee::getAge)));
        System.out.println(avgAgeOfMaleAndFemale);

        //Highest paid employee in orgnizations
        Optional<Employee> highestPaidEmployee = employeeList
                .stream().collect(Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary)));
        Employee highestPayEmployee = highestPaidEmployee.get();

        System.out.println("Details Of Highest Paid Employee : ");
        System.out.println("==================================");
        System.out.println("ID : " + highestPayEmployee.getId());
        System.out.println("Name : " + highestPayEmployee.getName());
        System.out.println("Age : " + highestPayEmployee.getAge());
        System.out.println("Gender : " + highestPayEmployee.getGender());
        System.out.println("Department : " + highestPayEmployee.getDepartment());
        System.out.println("Year Of Joining : " + highestPayEmployee.getYearOfJoining());
        System.out.println("Salary : " + highestPayEmployee.getSalary());
    }
}


