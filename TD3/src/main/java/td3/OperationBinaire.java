package td3;

import td3.p3.VariablesSymbolique;

public abstract class OperationBinaire implements ExpressionArithmetique {
	protected ExpressionArithmetique eaLeft;
	protected ExpressionArithmetique eaRight;

	public OperationBinaire(ExpressionArithmetique eaLeft, ExpressionArithmetique eaRight) {
		this.eaLeft = eaLeft;
		this.eaRight = eaRight;
	}

	protected ExpressionArithmetique simplifie(ExpressionArithmetique gauche, ExpressionArithmetique droite) {
		return this;
	}

	protected ExpressionArithmetique simplifie(ConstRationnelle gauche, ConstEntiere droite) {
		return this;
	}

	protected ExpressionArithmetique simplifie(ConstRationnelle gauche, ConstRationnelle droite) {
		return this;
	}

	protected ExpressionArithmetique simplifie(ConstEntiere gauche, ConstEntiere droite) {
		return this;
	}

	protected ExpressionArithmetique simplifie(ConstEntiere gauche, ConstRationnelle droite) {
		return this;
	}

	@Override
	public ExpressionArithmetique simplifier() {

		ExpressionArithmetique res;
		this.eaLeft = this.eaLeft.simplifier();
		this.eaRight = this.eaRight.simplifier();

		if (this.eaLeft instanceof ConstEntiere && this.eaRight instanceof ConstEntiere) {
			ConstEntiere gauche = (ConstEntiere) this.eaLeft;
			ConstEntiere droite = (ConstEntiere) this.eaRight;
			res = simplifie(gauche, droite);
		} else if (this.eaLeft instanceof ConstRationnelle && this.eaRight instanceof ConstRationnelle) {
			ConstRationnelle gauche = (ConstRationnelle) this.eaLeft;
			ConstRationnelle droite = (ConstRationnelle) this.eaRight;

			res = simplifie(gauche, droite);

		} else if (this.eaLeft instanceof ConstRationnelle && this.eaRight instanceof ConstEntiere)

		{
			ConstRationnelle gauche = (ConstRationnelle) this.eaLeft;
			ConstEntiere droite = (ConstEntiere) this.eaRight;

			res = simplifie(gauche, droite);

		} else if (this.eaLeft instanceof ConstEntiere && this.eaRight instanceof ConstRationnelle) {
			ConstEntiere gauche = (ConstEntiere) this.eaLeft;
			ConstRationnelle droite = (ConstRationnelle) this.eaRight;

			res = simplifie(gauche, droite);

		}
		/**
		 * ADDITION
		 */
		
		else if (this.eaLeft instanceof ExpressionArithmetique && this.eaRight instanceof VariablesSymbolique) {
			ConstEntiere gauche = new ConstEntiere(Integer.parseInt(String.valueOf(this.eaLeft.calculer())));
			res = this.simplifie(gauche, this.eaRight);

		}
		
		/**
		 * END ADDITION
		 */

		else {
			res = this;
		}

		return res;

	}

}
