import java.util.logging.Level;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;


public class DcLibs extends JavaPlugin {
	private FileConfiguration config_ = null;
	
	private final String plugin_name_ = "DcLibs";
	
	private Boolean need_greeting_ = true;
	private final String need_greeting_field_ = "greeting";
	private Boolean need_greeting_default_ = true;
	
	public DcLibs() {
		
	}
	
	private void reloadParams() {
		reloadConfig();
		config_ = getConfig();
		if (!config_.isBoolean(need_greeting_field_)) {
			config_.set(need_greeting_field_, need_greeting_default_);
		}
		need_greeting_ = config_.getBoolean(need_greeting_field_);
		saveConfig();
	}
	
	public String getPluginName() {
		return plugin_name_;
	}
	
	private void printGreetingInConsole() {
		String greeting = "\n===========================\n"
				+ "|   |     ___   ___        \n"
				+ "|   |\\   /|  |  |     \\  / \n"
				+ "|===| \\ / |__|  |__    \\/  \n"
				+ "|   |  |  |     |      /\\  \n"
				+ "|   |  |  |     |__   /  \\ \n"
				+ "===========================";
		getLogger().log(Level.INFO, greeting);
	}
	
	@Override
	public void onEnable() {
		reloadParams();
		if (need_greeting_) {
			printGreetingInConsole();
		}
	}
	
	@Override
	public void onDisable() {

	}
	
}
