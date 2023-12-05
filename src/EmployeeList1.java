import com.emp1.Employee1;

import java.sql.SQLOutput;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EmployeeList1 {
    public static void main(String[] args) {
        List<Employee1> empList = new ArrayList<>();
        empList.add(new Employee1(1, "abc", 28, 123, "F", "HR", "Blore", 2020));
        empList.add(new Employee1(2, "xyz", 29, 120, "F", "HR", "Hyderabad", 2015));
        empList.add(new Employee1(3, "efg", 30, 115, "M", "HR", "Chennai", 2014));
        empList.add(new Employee1(4, "def", 32, 125, "F", "HR", "Chennai", 2013));
        empList.add(new Employee1(5, "ijk", 22, 150, "F", "IT", "Noida", 2013));
        empList.add(new Employee1(6, "mno", 27, 140, "M", "IT", "Gurugram", 2017));
        empList.add(new Employee1(7, "uvw", 26, 130, "F", "IT", "Pune", 2016));
        empList.add(new Employee1(8, "pqr", 23, 145, "M", "IT", "Trivandam", 2015));
        empList.add(new Employee1(19, "stv", 25, 160, "M", "IT", "Blore", 2010));

        //Find Highest salary in the organisation.
        Optional<Employee1> empHighSalary = empList.stream().sorted(Comparator.comparingDouble(Employee1::getSalary).reversed()).findFirst();
        System.out.println("Highest Salary in the organisation :: " + empHighSalary.get().getSalary());

        //Find Second Highest salary in the organisation.
        Optional<Employee1> emp2HighSalary = empList.stream().sorted(Comparator.comparingDouble(Employee1::getSalary).reversed()).skip(1).findFirst();
        System.out.println("Second Highest Salary in the organisation :: " + emp2HighSalary.get().getSalary());

        //nth Highest salary in the organisation
        int n = 9;
        Optional<Employee1> NthHighSalary = empList.stream().sorted(Comparator.comparingDouble(Employee1::getSalary).reversed()).skip(n - 1).findFirst();
        System.out.println("Nth  Highest Salary in the organisation :: " + NthHighSalary.get().getSalary());

        //Find highest paid salary in the organisation based on gender.
        Map<String, Optional<Employee1>> highestPaidMFEmployee = empList.stream().collect(Collectors.groupingBy(Employee1::getGender,
                Collectors.maxBy((e1, e2) -> (int) (e1.getSalary() - e2.getSalary()))));
        System.out.println("Highest paid male and female employee :: " + highestPaidMFEmployee);

        //Find lowest paid salary in the organisation based in the organisation.
        Map<String, Optional<Employee1>> lowestPaidMFEmployee = empList.stream().collect(Collectors.groupingBy(Employee1::getGender,
                Collectors.minBy((e1, e2) -> (int) (e1.getSalary() - e2.getSalary()))));
        System.out.println("Lowest paid male and female employee :: " + lowestPaidMFEmployee);

        //Sort the employees salary in the organisation in ascending order
        System.out.println("Sorting the organisation's employee salary in ascending order ");
        empList.stream().sorted(Comparator.comparingLong(Employee1::getSalary)).toList().forEach(System.out::println);

        //Sort the employees salary in the organisation in decending order
        System.out.println("Sorting the organisation's employee salary in descending order ");
        empList.stream().sorted(Comparator.comparingLong(Employee1::getSalary).reversed()).toList().forEach(System.out::println);

        //Highest salary based on department.
        System.out.println("Highest salary dept wise:: \n" + empList.stream().collect(Collectors.groupingBy(Employee1::getDeptName,
                Collectors.collectingAndThen(Collectors.toList(), list -> list.stream().max(Comparator.comparingDouble(Employee1::getSalary))))));

        //Print list of employee’s second highest record based on department
        System.out.println("Highest second salary dept wise:: \n" + empList.stream().collect(Collectors.groupingBy(Employee1::getDeptName,
                Collectors.collectingAndThen(Collectors.toList(), list -> list.stream().sorted(Comparator.comparingDouble(Employee1::getSalary).reversed()).skip(1).findFirst()))));

        //Sort the employees salary in each department in ascending order
        System.out.println("Sorting the department wise employee salary in ascending order:: ");
        Map<String, Stream<Employee1>> sortedEmployeeAsc = empList.stream().collect(Collectors.groupingBy(Employee1::getDeptName,
                Collectors.collectingAndThen(Collectors.toList(), list -> list.stream().sorted(Comparator.comparingDouble(Employee1::getSalary)))));
        sortedEmployeeAsc.forEach((k, v) -> {
            System.out.println(k);
            System.out.println(v.collect(Collectors.toList()));
        });
        //sort the employees salary in each department in decending order
        System.out.println("Sorting the department wise employee salary in descending order ");
        Map<String, Stream<Employee1>> sortedEmployeeDesc = empList.stream().collect(Collectors.groupingBy(Employee1::getDeptName,
                Collectors.collectingAndThen(Collectors.toList(), list -> list.stream().sorted(Comparator.comparingDouble(Employee1::getSalary).reversed()))));
        sortedEmployeeDesc.forEach((k, v) -> {
            System.out.println(k);
            System.out.println(v.collect(Collectors.toList()));
        });

        // Group employees by city
        Map<String, List<Employee1>> empByCity = empList.stream().collect(Collectors.groupingBy(Employee1::getCity));
        System.out.println("Employees grouped by city :: \n" + empByCity);

        //Group the Employees by age.
        Map<Integer, List<Employee1>> empByAge = empList.stream().collect(Collectors.groupingBy(Employee1::getAge));
        System.out.println("Employees grouped by age :: \n" + empByAge);

        //Find distinct department names that employees work for.
        System.out.println("Distinct department names that employees work for:: ");
        empList.stream().map(Employee1::getDeptName).distinct().forEach(System.out::println);

        //Print the names of all departments in the organization.
        System.out.println("Names of all departments in the organization ");
        empList.stream().map(Employee1::getDeptName).distinct().forEach(System.out::println);

        //Find the count of male and female employees present in the organization.
        Map<String, Long> noOfMaleAndFemaleEmp = empList.stream().collect(Collectors.groupingBy(Employee1::getGender, Collectors.counting()));
        System.out.println("Count of male and female employees present in the organization:: \n" + noOfMaleAndFemaleEmp);

        //Print employee details whose age is greater than 28.
        System.out.println("Employee details whose age is > than 28");
        empList.stream().filter(e -> e.getAge() > 28).collect(Collectors.toList()).forEach(System.out::println);

        //Find maximum age of employee / for minimum age used min() method
        Optional<Employee1> maxAgeEmployee = empList.stream().max((e1, e2) -> Integer.compare(e1.getAge(), e2.getAge()));
        if (maxAgeEmployee.isPresent()) {
            System.out.println("Maximum age: " + maxAgeEmployee.get().getAge());
        } else {
            System.out.println("No employees found");
        }

        //OR
        OptionalInt max = empList.stream().mapToInt(Employee1::getAge).max();
        if (max.isPresent()) {
            System.out.println("Maximum age of Employee: " + max.getAsInt());
        }

        //Print Average age of Male and Female Employees.
        Map<String, Double> avgAge = empList.stream().collect(Collectors.groupingBy(Employee1::getGender, Collectors.averagingInt(Employee1::getAge)));
        System.out.println("Avg age of Male and Female Employees:: " + avgAge);

        // Print the number of employees in each department.
        Map<String, Long> countByDept = empList.stream().collect(Collectors.groupingBy(Employee1::getDeptName, Collectors.counting()));
        System.out.println("Number of employees in each department");
        for (Map.Entry<String, Long> entry : countByDept.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }

        //Find oldest employee.
        Optional<Employee1> oldestEmp = empList.stream().max(Comparator.comparingInt(Employee1::getAge));
        Employee1 oldestEmployee = oldestEmp.get();
        System.out.println("Oldest employee details:: \n" + oldestEmployee);

        //Find youngest female employee.
        Optional<Employee1> youngestEmp = empList.stream().filter(e -> e.getGender() == "F").min(Comparator.comparingInt(Employee1::getAge));
        Employee1 youngestEmployee = youngestEmp.get();
        System.out.println("Youngest Female employee details:: \n" + youngestEmployee);

        //Find employees whose age is greater than 30 & less than 30.
        System.out.println("Employees whose age is greater than 25 and less than 25");
        Map<Boolean, List<Employee1>> partitionEmployeesByAge = empList.stream().collect(Collectors.partitioningBy(e -> e.getAge() > 30));
        Set<Map.Entry<Boolean, List<Employee1>>> empSet = partitionEmployeesByAge.entrySet();
        for (Map.Entry<Boolean, List<Employee1>> entry : empSet) {
            if (Boolean.TRUE.equals(entry.getKey())) {
                System.out.println("Employees greater than 30 years ::" + entry.getValue());
            } else {
                System.out.println("Employees less than 30 years ::" + entry.getValue());
            }
        }

        //Find the department name which has the highest number of employees
        Map.Entry<String, Long> maxNoOfEmployeeInDepartment = empList.stream().collect(Collectors.groupingBy(Employee1::getDeptName, Collectors.counting()))
                .entrySet().stream().max(Map.Entry.comparingByValue()).get();
        System.out.println("MAX no of employees present in Department :: " + maxNoOfEmployeeInDepartment.getKey());

        //Find if there any employees from HR Department.
        Optional<Employee1> empAnyHRDept = empList.stream().filter(e -> e.getDeptName().equalsIgnoreCase("HR")).findAny();
        empAnyHRDept.ifPresent(emp -> System.out.println("Found Employees from HR department " + emp));

        //Find the department names that these employees work for, where the number of employees in the department is over 3.
        System.out.println("Department names where the number of employees in the department is over 3 :: ");
        empList.stream().collect(Collectors.groupingBy(Employee1::getDeptName, Collectors.counting())).
                entrySet().stream().filter(entry -> entry.getValue() > 3).forEach(System.out::println);

        //Find all employees who lives in ‘Blore’ city, sort them by their name and print the names of employees.
        empList.stream().filter(e -> e.getCity().equalsIgnoreCase("Blore"))
                .sorted(Comparator.comparing(Employee1::getName)).forEach(e -> System.out.println("Employees staying in Blore:: " + e.getName()));

        //No of employee orginasation
        System.out.println("No of employees in the organisation :: " + empList.stream().count());

        //Find employee count in every department
        Map<String, Long> employeeCountInDeptMap = empList.stream().collect(Collectors.groupingBy(Employee1::getDeptName, Collectors.counting()));
        System.out.print("Employee department and its count :- \n" + employeeCountInDeptMap);

        //Find the department which has the highest number of employees.
        Optional<Map.Entry<String, Long>> deptNameWithHighestEmp = employeeCountInDeptMap.entrySet().stream().max(Map.Entry.comparingByValue());
        if (deptNameWithHighestEmp.isPresent()) {
            System.out.println("Department which has the highest number of employees " + deptNameWithHighestEmp.get());
        }

        //Sorting a Stream by age and name fields
        System.out.println("Sorting Acendind a Stream based on age and name.");
        Comparator<Employee1> comparator1 = Comparator.comparing(Employee1::getName);
        Comparator<Employee1> comparator2 = Comparator.comparing(Employee1::getAge);
        empList.stream().sorted(comparator1.thenComparing(comparator2)).forEach(System.out::println);

        List<Employee1> sortedlist = empList.stream().sorted(Comparator.comparing(Employee1::getName).thenComparing(Employee1::getAge).reversed()).collect(Collectors.toList());
        System.out.println("Sorted in Decending order by age and name: ");
        sortedlist.forEach(System.out::println);

        //Highest experienced employees in the organization.
        Optional<Employee1> highestExp = empList.stream().sorted(Comparator.comparing(Employee1::getYearOfJoining)).findFirst();
        System.out.println("Senior Employee Details :" + highestExp.get());

        //Print average and total salary of the organization.
        DoubleSummaryStatistics empSal = empList.stream().collect(Collectors.summarizingDouble(Employee1::getSalary));
        System.out.println("Total Salary:" + empSal.getSum());
        System.out.println("Avg Salary :" + empSal.getAverage());

        //Print Average salary of each department.
        System.out.println("Print Average salary of each department");
        Map<String, Double> avgSalary = empList.stream().collect(Collectors.groupingBy(Employee1::getDeptName,
                Collectors.averagingDouble(Employee1::getSalary)));
        Set<Map.Entry<String, Double>> entrySet = avgSalary.entrySet();
        for (Map.Entry<String, Double> entry : entrySet) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}
