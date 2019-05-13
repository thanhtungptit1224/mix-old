package oca;

public class Pattern {
    private final String name;
    private final int age;

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Pattern(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public static PatternBuilder builder() {
        return new PatternBuilder();
    }

    static class PatternBuilder {
        private String name;
        private int age;

        public PatternBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public PatternBuilder withAge(int age) {
            this.age = age;
            return this;
        }

        public Pattern build() {
            return new Pattern(name, age);
        }

    }

}

