/*
 * Copyright 2014-2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.spring.asciidoctor.blockswitch;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;

/**
 * Provides the Docinfo for injecting the block switch into HTML output.
 *
 * @author Andy Wilkinson
 */
public class BlockSwitchDocinfo {

	public String header() {
		String css = readResource("/blockSwitch.css");
		String javascript = readResource("/blockSwitch.js");
		return String.format("<style>%n%s%n</style>%n"
				+ "<script src=\"https://cdnjs.cloudflare.com/ajax/libs/zepto/1.2.0/zepto.min.js\"></script>%n"
				+ "<script type=\"text/javascript\">%n%s%n</script>%n", css, javascript);
	}

	private String readResource(String name) {
		Reader reader = new InputStreamReader(getClass().getResourceAsStream(name));
		try {
			StringWriter writer = new StringWriter();
			char[] buffer = new char[8192];
			int read;
			while ((read = reader.read(buffer)) >= 0) {
				writer.write(buffer, 0, read);
			}
			return writer.toString();
		}
		catch (Exception ex) {
			throw new IllegalStateException("Failed to read '" + name + "'", ex);
		}
		finally {
			try {
				reader.close();
			}
			catch (IOException ex) {
				// Continue
			}
		}
	}

}
