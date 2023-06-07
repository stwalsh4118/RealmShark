/*
 * MIT License
 *
 * Copyright (c) 2019-2022 Jannis Weis
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package tomato.gui.themes;

import com.github.weisj.darklaf.annotations.SynthesiseLaf;
import com.github.weisj.darklaf.properties.icons.IconResolver;
import com.github.weisj.darklaf.theme.Theme;
import com.github.weisj.darklaf.theme.info.PresetIconRule;
import com.github.weisj.darklaf.theme.spec.ColorToneRule;
import com.google.auto.service.AutoService;

import javax.swing.*;
import java.util.Properties;

/**
 * A theme following the color scheme from the IntelliJ Darcula theme.
 *
 * @author Jannis Weis
 */
@AutoService(Theme.class)
@SynthesiseLaf
public class cozy extends Theme {

    @Override
    protected String getResourcePath() {
//        return "..\\..\\resources\\main\\cozy\\";
        return "/cozy/";
    }

//    @Override
//    public void loadDefaults(final Properties properties, final UIDefaults currentDefaults,
//                             final IconResolver iconResolver) {
//
//        Logger LOGGER = LogUtil.getLogger(Theme.class);
//
//
//        Properties default_properties = new Properties();
//
//        String resource_path = getPropertyFilePath("defaults");
//
//        String jar_path = cozy.class.getProtectionDomain().getCodeSource().getLocation().getPath();
//
//        String relative_path = new File(jar_path).getParent() + File.separator + resource_path;
//
//        try (InputStream input_stream = new FileInputStream(relative_path)) {
//            if (input_stream != null) {
//                default_properties.load(input_stream);
//            } else if (input_stream == null) {
//                LOGGER.log(Level.SEVERE, "null stream Could not load " + relative_path + ". File not found");
//            }
//        } catch (final Exception e) {
//            LOGGER.log(Level.SEVERE, "catch fail Could not load " + relative_path + ". File not found");
//            e.printStackTrace();
//        }
//
//        PropertyLoader.putProperties(default_properties, properties, currentDefaults, iconResolver);
//    }

    @Override
    public String getPrefix() {
        return "cozy";
    }

    @Override
    public String getName() {
        return "Cozy";
    }

    @Override
    protected Class<? extends Theme> getLoaderClass() {
        return cozy.class;
    }

    @Override
    public ColorToneRule getColorToneRule() {
        return ColorToneRule.DARK;
    }

    @Override
    protected PresetIconRule getPresetIconRule() {
        return PresetIconRule.DARK;
    }

    @Override
    public boolean supportsCustomAccentColor() {
        return true;
    }

    @Override
    public boolean supportsCustomSelectionColor() {
        return true;
    }

    @Override
    public void loadIconTheme(final Properties properties, final UIDefaults currentDefaults,
                              final IconResolver iconResolver) {
        super.loadIconTheme(properties, currentDefaults, iconResolver);
        loadCustomProperties("icons", properties, currentDefaults, iconResolver);
    }

}