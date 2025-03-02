/**
 * Copyright © 2025 ARNAB BANERJEE. All rights reserved.
 * <p>
 * This program is proprietary and confidential. It is licensed for use only by authorized users.
 * Unauthorized use, copying, distribution, or modification is strictly prohibited and may result
 * in severe civil and criminal penalties.
 * <p>
 * THIS PROGRAM IS PROVIDED 'AS IS' WITHOUT WARRANTY OF ANY KIND, EITHER EXPRESS OR IMPLIED, INCLUDING,
 * BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, OR NONINFRINGEMENT.
 * <p>
 * ARNAB BANERJEE DISCLAIMS ALL LIABILITY FOR DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY,
 * OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS PROGRAM, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package lambdacheck;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

class Employee {
    private int id;
    private String name;
    private String designation;
    private String city;

    public Employee(int id, String name, String designation, String city) {
        this.id = id;
        this.name = name;
        this.designation = designation;
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", designation='" + designation + '\'' +
                ", city='" + city + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}

public class Main {
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "Arnab", "Manager", "Kolkata"));
        employees.add(new Employee(2, "Sohini", "Manager", "Kolkata"));
        employees.add(new Employee(1, "Arnab", "Manager", "New Delhi"));
        employees.add(new Employee(2, "Sohini", "Manager", "New Delhi"));
        employees.add(new Employee(1, "Arnab", "Manager", "Mumbai"));
        employees.add(new Employee(2, "Sohini", "Manager", "Mumbai"));

        employees
                .stream()
                .filter(employee -> employee.getCity().equalsIgnoreCase("Kolkata"))
                .forEach(System.out::println);
        System.out.println("===================================================================================");

        employees
                .stream()
                .filter(employee -> employee.getCity().equalsIgnoreCase("New Delhi"))
                .forEach(System.out::println);
        System.out.println("===================================================================================");

        employees
                .stream()
                .filter(employee -> employee.getCity().equalsIgnoreCase("Mumbai"))
                .forEach(System.out::println);
        System.out.println("===================================================================================");

        employees
                .stream()
                .distinct()
                .forEach(System.out::println);
        System.out.println("===================================================================================");

        Set<Integer> idSet = new HashSet<>();
        employees
                .stream()
                .filter(employee -> idSet.add(employee.getId()))
                .forEach(System.out::println);
    }
}
