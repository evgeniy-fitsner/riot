/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.riotfamily.core.dao;

import java.util.ArrayList;
import java.util.Collection;

import org.riotfamily.common.beans.property.PropertyUtils;
import org.riotfamily.common.util.Generics;
import org.springframework.beans.PropertyAccessor;
import org.springframework.beans.support.PropertyComparator;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.RecoverableDataAccessException;

public abstract class InMemoryRiotDao extends RiotDaoAdapter 
		implements Sortable {

	@Override
	public Collection<?> list(Object parent, ListParams params)
			throws DataAccessException {
		
		try {
			Collection<?> items = listInternal(parent);
			ArrayList<Object> list = Generics.newArrayList(items.size());
			for (Object item : items) {
				if (filterMatches(item, params) && searchMatches(item, params)) {
					list.add(item);
				}
			}
			if (params.getOrder() != null && params.getOrder().size() > 0) {
				Order order = params.getOrder().get(0);
				PropertyComparator.sort(list, order);
			}
			return list;
		}
		catch (Exception e) {
			throw new RecoverableDataAccessException(e.getMessage(), e);
		}
	}
	
	protected boolean filterMatches(Object item, ListParams params) {
		if (params.getFilteredProperties() != null) {
			PropertyAccessor itemAccessor = PropertyUtils.createAccessor(item);
			PropertyAccessor filterAccessor = PropertyUtils.createAccessor(params.getFilter());
			for (String prop : params.getFilteredProperties()) {
				Object filterValue = filterAccessor.getPropertyValue(prop);
				if (filterValue != null) {
					Object itemValue = itemAccessor.getPropertyValue(prop);
					if (itemValue instanceof Collection<?>) {
						Collection<?> c = (Collection<?>) itemValue;
						if (!c.contains(filterValue)) {
							return false;
						}
					}
					else if (!filterValue.equals(itemValue)) {
						return false;
					}
				}
			}
		}
		return true;
	}
	
	protected boolean searchMatches(Object item, ListParams params) {
		if (params.getSearch() == null) {
			return true;
		}
		PropertyAccessor itemAccessor = PropertyUtils.createAccessor(item);
		for (String prop : params.getSearchProperties()) {
			Object itemValue = itemAccessor.getPropertyValue(prop);
			if (itemValue != null && itemValue.toString().indexOf(params.getSearch()) >= 0) {
				return true;
			}
		}
		return false;
	}
	
	protected abstract Collection<?> listInternal(Object parent) throws Exception;
	
}
