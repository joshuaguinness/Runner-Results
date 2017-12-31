package runner.results;

public class Runners {

    // Variable declaration
    public String name;
    public char sex;
    public int age;
    public double time;

    // Variable Initialization
    public Runners(String n, char s, int a, double t) {
        name = n;
        sex = s;
        age = a;
        time = t;
    }

    // Returns the name
    public String getName() {
        return name;
    }

    // Returns the sex
    public String getSex() {
        switch (sex) {
            case 'm':
                return "male";
            case 'f':
                return "female";
            default:
                return "other";
        }
    }

    // Returns the age
    public int getAge() {
        return age;
    }

    // Returns the time
    public double getTime() {
        return time;
    }

    // Changes the name
    public void changeName(String newName) {
        name = newName;
    }

    // Changes the sex
    public void changeSex(char newSex) {
        sex = newSex;
    }

    // Changes the age
    public void changeAge(int newAge) {
        age = newAge;
    }

    // Changes the time
    public void changeTime(double newTime) {
        time = newTime;
    }

    // Places all the info in one string so it is easy to output
    public String getInfo() {
        return name + " " + sex + " " + age + " " + time;
    }
}
