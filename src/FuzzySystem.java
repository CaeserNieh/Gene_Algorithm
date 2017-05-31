


public class FuzzySystem {
	private double[] d1 = new double[3];
	private double[] d2 = new double[3];
	private double alpha_d1, alpha_d2;

	public FuzzySystem() {
		// TODO Auto-generated constructor stub
		for (int i = 0; i < 3; i++) {
			d1[i] = 0;
			d2[i] = 0;
		}
		alpha_d1 = 0;
		alpha_d2 = 0;
	}

	public void Right_Left(double Right, double Left) {//
		double dist = Right - Left;
		if (dist <= -10) {
			if (dist <= -130) {
				alpha_d1 = 1;
			} else {
				alpha_d1 = dist * -1 / 120 - 0.1;
			}
			d1[0] = 1;
		}
		if (dist >= -12.5 && dist <= 12.5) {
			if (dist < 0) {
				alpha_d1 = dist * 1 / 12.5 + 1;
			} else if (dist > 0) {
				alpha_d1 = dist * -1 / 12.5 + 1;
			} else {
				alpha_d1 = 1;
			}
			d1[1] = 1;
		}
		if (dist >= 10) {
			if (dist >= 150) {
				alpha_d1 = 1;
			} else {
				alpha_d1 = dist * 1 / 140 - 0.2;
			}
			d1[2] = 1;
		}
	}

	public void Front(double dist) {
		if (dist <= 20) {
			if (dist <= 15) {
				alpha_d2 = 1;
			} else {
				alpha_d2 = dist * -1 / 5 + 4;
			}
			d2[0] = 1;
		}
		if (dist > 20 && dist < 50) {
			alpha_d2 = 1;
			d2[1] = 1;
		}
		if (dist >= 35) {
			if (dist >= 60) {
				alpha_d2 = 1;
			} else {
				alpha_d2 = dist * 1 / 25 - 1.4;
			}
			d2[2] = 1;
		}
	}

	public double Defuzzification() {

		double threshold;
		double num_a = 0;
		double num_b = 0;
		double theta = 0;
		for (int i = -60; i <= 60; i++) {
			double[] alpha = { 0, 0, 0, 0, 0, 0, 0, 0 };
			double maxalpha = 0;
			if (d1[2] == 1 && d2[0] == 1) {
				alpha[1] = Math.min(alpha_d1, alpha_d2);
				threshold = -40 * alpha[1];
				if (i < 0 && i >= -40) {
					if (i > threshold) {
						alpha[1] = i * -1 / 40;
					}
				} else {
					alpha[1] = 0;
				}
			}
			if (d1[0] == 1 && d2[0] == 1) {
				alpha[2] = Math.min(alpha_d1, alpha_d2);
				threshold = 40 * alpha[2];
				if (i > 0 && i <= 40) {
					if (i < threshold) {
						alpha[2] = i * 1 / 40;
					}
				} else {
					alpha[2] = 0;
				}
			}
			if (d1[1] == 1 && d2[0] == 1) {
				if (i == -35) {
					alpha[3] = Math.min(alpha_d1, alpha_d2);
				} else {
					alpha[3] = 0;
				}
			}
			if (d1[2] == 1 && d2[1] == 1) {
				alpha[4] = Math.min(alpha_d1, alpha_d2);
				threshold = -20 * alpha[4];
				if (i < 0 && i >= -35) {
					if (i > threshold) {
						alpha[4] = i * -1 / 20;
					}
				} else {
					alpha[4] = 0;
				}
			}
			if (d1[0] == 1 && d2[1] == 1) {
				alpha[5] = Math.min(alpha_d1, alpha_d2);
				threshold = 20 * alpha[5];
				if (i > 0 && i <= 35) {
					if (i < threshold) {
						alpha[5] = i * 1 / 20;
					}
				} else {
					alpha[5] = 0;
				}
			}
			if (d1[2] == 1 && d2[2] == 1) {
				alpha[6] = Math.min(alpha_d1, alpha_d2);
				threshold = -15 * alpha[6];
				if (i < 0 && i >= -15) {
					if (i > threshold) {
						alpha[6] = i * -1 / 15;
					}
				} else {
					alpha[6] = 0;
				}
			}
			if (d1[0] == 1 && d2[2] == 1) {
				alpha[7] = Math.min(alpha_d1, alpha_d2);
				threshold = 15 * alpha[7];
				if (i > 0 && i <= 15) {
					if (i < threshold) {
						alpha[7] = i * 1 / 15;
					}
				} else {
					alpha[7] = 0;
				}
			}
			for (int j = 1; j <= 7; j++) {
				if (alpha[j] > maxalpha) {
					maxalpha = alpha[j];
				}
			}

			num_a = num_a + i * maxalpha;
			num_b = num_b + maxalpha;
		}

		if (num_b != 0) {
			theta = num_a / num_b;
		}
		return theta;
	}

}