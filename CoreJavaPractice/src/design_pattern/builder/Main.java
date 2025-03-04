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

package design_pattern.builder;

class FactoryClass {
    private String name;
    private String city;

    public FactoryClass(String name, String city) {
        this.name = name;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}

class FactoryClassBuilder {
    private String name;
    private String city;

    public static FactoryClassBuilder builder() {
        return new FactoryClassBuilder();
    }

    public FactoryClassBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public FactoryClassBuilder setCity(String city) {
        this.city = city;
        return this;
    }

    public FactoryClass build() {
        return new FactoryClass(name, city);
    }
}

public class Main {
    public static void main(String[] args) {
        FactoryClass firstPerson = FactoryClassBuilder
                .builder()
                .setName("Arnab")
                .setCity("Kolkata")
                .build();

        FactoryClass secondPerson = FactoryClassBuilder
                .builder()
                .setName("Sohini")
                .setCity("Kolkata")
                .build();

        System.out.println("First Person: " + firstPerson.getName() +
                ", City: " + firstPerson.getCity());
        System.out.println("Second Person: " + secondPerson.getName() +
                ", City: " + secondPerson.getCity());
    }
}
