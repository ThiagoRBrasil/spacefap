package ranking;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import util_Banco.HibernateUtil;

public class RankingDAO {

    private Session sessao;
    private Transaction transacao;

    public void salvar(Ranking ranking) {
        sessao = HibernateUtil.getSessionFactory().
                openSession();
        transacao = sessao.beginTransaction();
        sessao.save(ranking);
        transacao.commit();
        sessao.close();
    }

    public void atualizar(Ranking ranking) {
        sessao = HibernateUtil.getSessionFactory().
                openSession();
        transacao = sessao.beginTransaction();
        sessao.update(ranking);
        transacao.commit();
        sessao.close();
    }

    public void excuir(Ranking ranking) {
        sessao = HibernateUtil.getSessionFactory().
                openSession();
        transacao = sessao.beginTransaction();
        sessao.delete(ranking);
        transacao.commit();
        sessao.close();
    }
    
    public List<Ranking> listar(int pontuacao) {
        sessao = HibernateUtil.getSessionFactory().
                openSession();
        List<Ranking> lista
                = sessao.createCriteria(Ranking.class)
                .add(Restrictions.eq("pontuacao", pontuacao))
                .list();
        sessao.close();
        return lista;
    }

    public List<Ranking> listar() {
        sessao = HibernateUtil.getSessionFactory().
                openSession();
        List<Ranking> lista
                = sessao.createCriteria(Ranking.class)
                .list();
        sessao.close();
        return lista;
    }

}
