package ranking;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Ranking {

    @Id
    private int pontuacao;

    public int getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

}
