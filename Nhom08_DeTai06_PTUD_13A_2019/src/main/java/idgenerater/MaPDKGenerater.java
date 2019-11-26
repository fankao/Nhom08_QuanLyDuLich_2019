package idgenerater;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class MaPDKGenerater implements IdentifierGenerator {

	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
		String query = "SELECT t.maPhieuDK FROM PhieuDangKy t ORDER BY LENGTH(t.maPhieuDK)";
		List<String> lstId = session.createQuery(query, String.class).list();
		if (lstId.size() != 0) {

			int max = Integer.parseInt(lstId.get(0).split("/")[0].substring(5));
			for (String x : lstId) {
				if (Integer.parseInt(x.split("/")[0].substring(5)) > max) {
					max = Integer.parseInt(lstId.get(0).split("/")[0].substring(5));
				}
			}
			return "PDK00" + (max + 1) + LocalDate.now().getMonthValue() + "" + LocalDate.now().getYear();
		}
		return "PDK001/" + LocalDate.now().getMonthValue() + "" + LocalDate.now().getYear();
	}

}
