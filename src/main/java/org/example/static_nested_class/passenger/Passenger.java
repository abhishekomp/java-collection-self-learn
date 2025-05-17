package org.example.static_nested_class.passenger;

public class Passenger {
    private final String name;
    private RewardProgram rewardProgram = new RewardProgram();

    public Passenger(String name) {
        this.name = name;
    }
    public Passenger(String name, int memberLevel, int memberDays) {
        this(name);
        rewardProgram.memberLevel = memberLevel;
        rewardProgram.memberDays = memberDays;
    }
    private static class RewardProgram {
        private int memberLevel;
        private int memberDays;

        public RewardProgram() {
            this.memberLevel = 0;
            this.memberDays = 0;
        }

        @Override
        public String toString() {
            return "RewardProgram{" +
                    "memberLevel=" + memberLevel +
                    ", memberDays=" + memberDays +
                    '}';
        }

        //        public RewardProgram(int memberLevel, int memberDays) {
//            this.memberLevel = memberLevel;
//            this.memberDays = memberDays;
//        }
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "name='" + name + '\'' +
                ", rewardProgram=" + rewardProgram +
                '}';
    }
}
