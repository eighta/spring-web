package web.customs;

import java.util.Map;

import org.springframework.context.MessageSource;
import org.springframework.ui.context.Theme;
import org.springframework.ui.context.support.ResourceBundleThemeSource;
import org.springframework.ui.context.support.SimpleTheme;

import a8.utils.CommonsUtils;

public class ClassPathThemeSource extends ResourceBundleThemeSource{

	//XXX TRATAR DE IMPLEMENTAR
	
	private final CommonsUtils commonsUtils = CommonsUtils.getInstance();
	
	@Override
	public Theme getTheme(String themeName) {
		if (themeName == null) {
			return null;
		}
		Theme theme = this.getThemeCache().get(themeName);
		if (theme == null) {
			synchronized (this.getThemeCache()) {
				theme = this.getThemeCache().get(themeName);
				if (theme == null) {
					String basename = this.getBasenamePrefix() + themeName;
					MessageSource messageSource = createMessageSource(basename);
					theme = new SimpleTheme(themeName, messageSource);
					initParent(theme);
					this.getThemeCache().put(themeName, theme);
					if (logger.isDebugEnabled()) {
						logger.debug("Theme created: name '" + themeName + "', basename [" + basename + "]");
					}
				}
			}
		}
		return theme;
	}

	private String getBasenamePrefix() {
		// TODO Auto-generated method stub
		return null;
	}

	private Map<String, Theme> getThemeCache() {
		// TODO Auto-generated method stub
		return null;
	}
}
