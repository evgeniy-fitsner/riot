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
package org.riotfamily.media.riot.ui;

import java.io.PrintWriter;

import org.riotfamily.common.ui.ObjectRenderer;
import org.riotfamily.common.ui.RenderContext;
import org.riotfamily.media.model.RiotImage;

public class RiotImageRenderer implements ObjectRenderer {

	public void render(Object obj, RenderContext context, PrintWriter writer) {
		RiotImage image = (RiotImage) obj;
		if (image != null) {
			writer.format("<img src=\"%s\" width=\"%s\" height=\"%s\" />",
					context.getContextPath() + image.getUri(), 
					image.getWidth(), image.getHeight());
		}
	}

}
