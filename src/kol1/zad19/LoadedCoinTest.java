package kol1.zad19;

import java.util.Random;
import java.util.Scanner;

enum SIDE {
	HEAD, TAIL
}
class Coin {

	SIDE side;

	public SIDE flip() {
		Random random = new Random();
		boolean isHead = random.nextBoolean();
		if (isHead) {
			return SIDE.HEAD;
		} else {
			return SIDE.TAIL;
		}
	}
}

class LoadedCoin extends Coin {

    private int probability;
    private Random random;

    // probability is the chance (0 - 100 %) that a flip returns HEAD
    public LoadedCoin(int probability) {
        this.probability = probability;
        this.random = new Random();
    }

    @Override
    public SIDE flip() {
        // nextInt(100) is uniform over 0..99, so it is below P with probability P%
        if (random.nextInt(100) < probability) {
            return SIDE.HEAD;
        } else {
            return SIDE.TAIL;
        }
    }
}

public class LoadedCoinTest {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int probability = scanner.nextInt();
		Coin c = new Coin();
		int heads = 0;
		int n = 1000;
		for(int i = 0; i < n; i++) {
			SIDE side = c.flip();
			if(side == SIDE.HEAD) {
				heads++;
			}
		}
		if(heads > 450 && heads < 550) {
			System.out.println("YES");
		} else {
            System.out.println("NO");
        }
		c = new LoadedCoin(probability);
		heads = 0;
		for(int i = 0; i < n; i++) {
			SIDE side = c.flip();
			if(side == SIDE.HEAD) {
				heads++;
			}
		}
		if(heads > probability * 10 - 50 && heads < probability * 10 + 50) {
			System.out.println("YES");
		} else {
            System.out.println("NO");
        }
	}
}
