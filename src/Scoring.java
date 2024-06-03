    class Scoring {
        private int score;

        //Scoring system points
        private static final int SCORE_STOCK_TO_TABLEAU = 5;
        private static final int SCORE_TABLEAU_TO_FOUNDATION = 10;
        private static final int SCORE_TURNOVER_TABLEAU_CARD = 5;
        private static final int SCORE_FOUNDATION_BACK_TO_TABLEAU = -15;

        public Scoring() {
            this.score = 0;
        }

        public void addToScore(int points) {
            score += points;
        }

        public void deductFromScore(int points) {
            score -= points;
        }

        public int getScore() {
            return score;
        }

       //Moving card scoring system
        public void scoreStockToTableau() {
            score += SCORE_STOCK_TO_TABLEAU;
        }

        public void scoreTableauToFoundation() {
            score += SCORE_TABLEAU_TO_FOUNDATION;
        }

        public void scoreTurnoverTableauCard() {
            score += SCORE_TURNOVER_TABLEAU_CARD;
        }

        public void scoreFoundationBackToTableau() {
            score += SCORE_FOUNDATION_BACK_TO_TABLEAU;
        }
    }