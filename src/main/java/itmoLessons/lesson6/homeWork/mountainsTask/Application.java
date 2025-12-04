package itmoLessons.lesson6.homeWork.mountainsTask;


public class Application {
    public static void main(String[] args) {
        Climber climber1 = new Climber();
        climber1.setName("Petr");
        climber1.setAddress("Lahta Park 3");
        Climber climber2 = new Climber("Tom", "13 Street");
        Climber climber3 = new Climber("John", "14 Street");
        Climber climber4 = new Climber("Ira", "15 Street");
        Climber climber5 = new Climber("Leha", "16 Street");
        Climber climber6 = new Climber("Vlad", "17 Street");


        Mountain araratMountain = new Mountain();
        araratMountain.setHighOfMountain(2342);
        araratMountain.setLandOfMountain("Армения");
        araratMountain.setNameOfMountain("Арарат");

        Mountain alpsMountain = new Mountain();
        alpsMountain.setHighOfMountain(2342);
        alpsMountain.setLandOfMountain("Альпы");
        alpsMountain.setNameOfMountain("Австрия");

        Mountain chogorayMountain = new Mountain();
        chogorayMountain.setHighOfMountain(2342);
        chogorayMountain.setLandOfMountain("Карокурум");
        chogorayMountain.setNameOfMountain("Австрия");

        Group group1 = new Group(true, araratMountain);
        group1.addClimberToGroup(climber1);
        group1.addClimberToGroup(climber2);
        group1.addClimberToGroup(climber3);
        group1.isGroupUnAvailable(false);
        System.out.println(group1);

        Group group2 = new Group(true, alpsMountain);
        group2.addClimberToGroup(climber1);
        group2.addClimberToGroup(climber2);
        group2.addClimberToGroup(climber3);
        group2.addClimberToGroup(climber4);
        group2.addClimberToGroup(climber5);
        group2.addClimberToGroup(climber6);
        System.out.println(group2);

    }
}
