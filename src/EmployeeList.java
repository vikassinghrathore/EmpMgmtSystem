import com.emp.Employee;

import java.util.*;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;

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
        employeeList.add(new Employee(199, "Cameron green", 25, "Female", "Sales & Marketing", 2016, 11500.0));
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

        //4.Highest paid employee in orgnizations
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

        //5.employee how joined after 2015
        employeeList.stream().filter(e -> e.getYearOfJoining() > 2015).map(Employee::getName).forEach(System.out::println);

        //6.count the no of employee each deparment
        Map<String, Long> countNoOfEmployeeEachDept = employeeList.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));
        Set<Entry<String, Long>> entrySet = countNoOfEmployeeEachDept.entrySet();
        for (Entry<String, Long> entry : entrySet) {
            System.out.println(entry.getKey() + "" + entry.getValue());
        }
        //7.average salary of each department
        Map<String, Double> avgSalaryOfEachDept = employeeList
                .stream().collect(Collectors.groupingBy(Employee::getDepartment, Collectors.averagingDouble(Employee::getSalary)));
        Set<Entry<String, Double>> entrySet1 = avgSalaryOfEachDept.entrySet();
        for (Entry<String, Double> doubleEntry : entrySet1) {
            System.out.println(doubleEntry.getKey() + "" + doubleEntry.getValue());
        }
        //8.Details of Youngest male employee in the product development department
        Optional<Employee> youngestMaleEmployeeProductDevelopmentDepartmentWrapper = employeeList
                .stream().filter(e -> e.getGender() == "Male" && e.getDepartment() == "Product Development")
                .min(Comparator.comparing(Employee::getAge));
        Employee youngestMaleEmployeeProductDevelopmentDepartment = youngestMaleEmployeeProductDevelopmentDepartmentWrapper.get();

        System.out.println("Details Of Youngest Male Employee In Product Development");
        System.out.println("----------------------------------------------");
        System.out.println("ID : " + youngestMaleEmployeeProductDevelopmentDepartment.getId());
        System.out.println("Name : " + youngestMaleEmployeeProductDevelopmentDepartment.getName());
        System.out.println("Age : " + youngestMaleEmployeeProductDevelopmentDepartment.getAge());
        System.out.println("Year Of Joinging : " + youngestMaleEmployeeProductDevelopmentDepartment.getYearOfJoining());
        System.out.println("Salary : " + youngestMaleEmployeeProductDevelopmentDepartment.getSalary());

        //9.Most working experience in the organization
        Optional<Employee> mostWorkingExperienceInOrg = employeeList
                .stream().sorted(Comparator.comparingInt(Employee::getYearOfJoining)).findFirst();
        Employee mostWorkingExperience = mostWorkingExperienceInOrg.get();
        System.out.println("Seniour most employee details");
        System.out.println("ID : " + mostWorkingExperience.getId());
        System.out.println("Name : " + mostWorkingExperience.getName());
        System.out.println("Age : " + mostWorkingExperience.getAge());
        System.out.println("Gender : " + mostWorkingExperience.getGender());
        System.out.println("Age : " + mostWorkingExperience.getDepartment());
        System.out.println("Year Of Joining : " + mostWorkingExperience.getYearOfJoining());
        System.out.println("Salary : " + mostWorkingExperience.getSalary());

        //10.How many male and female employees are there in the sales and marketing dept
        Map<String, Long> countMaleFemaleEmployeeSalesAndMarketing = employeeList
                .stream()
                .filter(e -> e.getDepartment() == "Sales & Marketing")
                .collect(Collectors.groupingBy(Employee::getGender, Collectors.counting()));
        System.out.println("CountMaleFemaleEmployeeSalesAndMarketing = " + countMaleFemaleEmployeeSalesAndMarketing);

        //11.average salary of male and female employees
        Map<String, Double> avgSalMaleAndfemaleEmplyee = employeeList
                .stream()
                .collect(Collectors.groupingBy(Employee::getGender, Collectors.averagingDouble(Employee::getSalary)));
        System.out.println("AvgSalMaleAndfemaleEmplyee = " + avgSalMaleAndfemaleEmplyee);

        //12.List down the names of all employees in each department
        Map<String, List<Employee>> employeeListByDepartment = employeeList
                .stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
        Set<Entry<String, List<Employee>>> entrySet2 = employeeListByDepartment.entrySet();
        for (Entry<String, List<Employee>> entry : entrySet2) {
            System.out.println("++++++++++++++++++++++++");
            System.out.println("Employees In " + entry.getKey() + " : ");
            System.out.println("++++++++++++++++++++++++");
            List<Employee> list = entry.getValue();
            for (Employee e : list) {
                System.out.println(e.getName());
            }
        }
        //13.avg sal and total sal
        DoubleSummaryStatistics employeeSalStatics = employeeList
                .stream().collect(Collectors.summarizingDouble(Employee::getSalary));
        System.out.println("Avg Sal:" + employeeSalStatics.getAverage());
        System.out.println("Total sal: " + employeeSalStatics.getSum());

        //14. Oldest employee in the organization? What is his age and which department belongs
        Optional<Employee> oldestEmployeeInOrg = employeeList.stream().max(Comparator.comparingInt(Employee::getAge));
        Employee oldEmployee = oldestEmployeeInOrg.get();
        System.out.println("Name : " + oldEmployee.getName());
        System.out.println("Age : " + oldEmployee.getAge());
        System.out.println("Department : " + oldEmployee.getDepartment());

        //15.Separate the employees who are younger or equal to 25 years from those employees who are older than 25 years.
        Map<Boolean, List<Employee>> partitionByEmpAge = employeeList.stream().collect(Collectors.partitioningBy(e -> e.getAge() > 25));
        Set<Entry<Boolean, List<Employee>>> entrySet3 = partitionByEmpAge.entrySet();
        for (Entry<Boolean, List<Employee>> entry : entrySet3) {
            System.out.println("+++++++++++++++++++++++++");
            if (entry.getKey()) {
                System.out.println("Employees older than 25 years :");
            } else {
                System.out.println("Employees younger than or equal to 25 years :");
            }
            System.out.println("+++++++++++++++++++++++++");
            List<Employee> list = entry.getValue();
            for (Employee e : list) {
                System.out.println(e.getName());
            }
            //sorting on name,age,dept
            Collections.sort(employeeList, Comparator.comparing(Employee::getName).thenComparingInt(Employee::getAge));

           // final Function<Employee, Integer> byAge = emplist -> emplist.getAge();
            //final Function<Employee, String> byTheirName = emplist -> emplist.getName();
            //System.out.println("Sorted in ascending order by age and name: ");
            List<Employee> sortedlist =   employeeList.stream()
                    .sorted(Comparator.comparing(Employee::getName).thenComparing(Employee::getAge).thenComparing(Employee::getDepartment))
                    .collect(Collectors.toList());
            sortedlist.forEach(System.out::println);
        }
    }
}


