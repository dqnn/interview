package design;
public abstract class RateLimiter {
/*
 * dive deep reading:
 * https://hechao.li/2018/06/27/Rate-Limiter-Part2/
 */
  protected final int maxRequestPerSec;

  protected RateLimiter(int maxRequestPerSec) {
    this.maxRequestPerSec = maxRequestPerSec;
  }

  abstract boolean allow();
}