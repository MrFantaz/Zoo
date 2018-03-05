package animals;

public class RoboCat  extends Feline {

    public  static  final double DEFAULT_CAT_SIZE = 21.2;
    public static  int catCounter;
    private VoiceModule voiceModule = new VoiceModule();

    public RoboCat() {
        super("RoboCat #"+catCounter++, DEFAULT_CAT_SIZE);
        this.type = "Robot";
    }

    @Override
    public void makeSound() {
    System.out.println(voiceModule.getRoboSound());
    }

    @Override
    public double jump() {
        return super.jump();
    }

    public VoiceModule getVoiceModule() {
        return voiceModule;
    }
    public void setVoiceModule(VoiceModule voiceModule) {
        this.voiceModule = voiceModule;
    }

    public static class VoiceModule{
        private String roboSound="000000";

        public VoiceModule (){
        }
        public VoiceModule (String roboSound){
            this.roboSound = roboSound;
        }
        public String getRoboSound() {
            return roboSound;
        }

        public void setRoboSound(String roboSound) {
            this.roboSound = roboSound;
        }
    }
}
