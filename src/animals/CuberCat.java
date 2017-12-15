package animals;

public class CuberCat extends Feline {
	private static final double default_Cat_Size = 21.2;
	private static int catCount;
	private VoiceModule module = new VoiceModule();

	public CuberCat() {
		super("CuberCat # " + catCount++, default_Cat_Size);
		setType("CuberCat");

	}

	@Override
	public void sound() {
		System.out.println(module.getCuberSound());
	}

	public static class VoiceModule {
		private String cuberSound="000000";

		
		
		public String getCuberSound() {
			return cuberSound;
		}

		public void setCuberSound(String cuberSound) {
			this.cuberSound = cuberSound;
		}

	}
}
