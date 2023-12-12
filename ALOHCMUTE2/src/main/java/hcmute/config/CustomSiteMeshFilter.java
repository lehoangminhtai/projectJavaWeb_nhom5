package hcmute.config;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;

public class CustomSiteMeshFilter extends ConfigurableSiteMeshFilter{
	@Override

	protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {

		// Assigning default decorator if no path specific decorator found

		builder.addDecoratorPath("/*", "/admin.jsp")

				// Map decorators to specific path patterns.

				.addDecoratorPath("/admin/*", "/admin.jsp")

				// Exclude few paths from decoration.

				.addExcludedPath("/login*").addExcludedPath("/login/*")
				
				.addExcludedPath("/register*").addExcludedPath("/register/*")

				.addExcludedPath("/alogin*").addExcludedPath("/alogin/*")

				.addExcludedPath("/api/**").addExcludedPath("/api/**");

	}
}
