package cf.vodes.osubot.irc;

public class Timer {
	
	private long lastMS = 0L;

	public boolean isDelayComplete(long delay) {
		if (System.currentTimeMillis() - this.lastMS >= delay) {
			return true;
		}
		return false;
	}

	public boolean hasReached(long milliseconds) {
		return getCurrentMS() - this.lastMS >= milliseconds;
	}

	public void setLastMS(long lastMS) {
		this.lastMS = lastMS;
	}

	public final void updateLastMS() {
		this.lastMS = System.currentTimeMillis();
	}

	public static final boolean hasTimePassed(final double d) {
		return hasTimePassed(d);
	}

	public final boolean hasTimePassedM(final long MS) {
		return getCurrentMS() >= this.lastMS + MS;
	}

	public final boolean hasTimePassedS(final float speed) {
		return getCurrentMS() >= this.lastMS + (long) (1000.0f / speed);
	}

	public void setLastMS() {
		this.lastMS = System.currentTimeMillis();
	}

	public int convertToMS(int perSecond) {
		return 1000 / perSecond;
	}

	public long getCurrentMS() {
		return System.nanoTime() / 1000000L;
	}

	public long getLastMS() {
		return this.lastMS;
	}

	public void reset() {
		this.lastMS = getCurrentMS();
	}

	public boolean hasReached(final float f) {
		return this.getCurrentMS() - this.lastMS >= f;
	}

	public boolean delay(final float milliSec) {
		return this.getCurrentMS() - this.lastMS >= milliSec;
	}

}