package CODU_RMI;

public class Local {

    private int x;
    private int y;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String toString() {
        return "Local{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public static class Posto extends Local{

        private String descricao;
        private int total_mob;

        public Posto(String descricao, int x, int y) {
            this.descricao = descricao;
            super.setX(x);
            super.setY(y);
        }

        public String getDescricao() {
            return descricao;
        }

        public void setDescricao(String descricao) {
            this.descricao = descricao;
        }

        public int getTotal_mob() {
            return total_mob;
        }

        public void setTotal_mob(int total_mob) {
            this.total_mob = total_mob;
        }

        public void addTotal_mob() {
            this.total_mob += 1;
        }

        public String toString() {
            return "Posto{" +
                    super.toString() +
                    ", descricao='" + descricao + '\'' +
                    ", total_mob=" + total_mob +
                    '}';
        }
    }
}

