package idgenerater;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class MaKHGenerater implements IdentifierGenerator {

	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
		String query = "SELECT t.maKH FROM KhachHang t ORDER BY SUBSTRING(t.maKH,5,LENGTH(t.maKH)-4)";
		List<String> lstId = session.createQuery(query, String.class).list();
		if (lstId.size() != 0) {
			int max = Integer.parseInt(lstId.get(0).substring(4));
			for (String x : lstId) {
				if (Integer.parseInt(x.substring(4)) > max) {
					max = Integer.parseInt(x.substring(4));
				}
			}
			return "KH00" + (max + 1);
		}
		return "KH001";
	}

}