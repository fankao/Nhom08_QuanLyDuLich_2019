package idgenerater;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class MaKHTGGenerater implements IdentifierGenerator {

	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
		String query = "SELECT t.maKHTG FROM KhachHangThamGia t";
		List<String> lstId = session.createQuery(query, String.class).list();
		if (lstId.size() != 0) {
			int max = Integer.parseInt(lstId.get(0).substring(6));
			for (String x : lstId) {
				if (Integer.parseInt(x.substring(6)) > max) {
					max = Integer.parseInt(x.substring(6));
				}
			}
			return "KHTG00" + (max + 1);
		}
		return "KHTG001";
	}

}
