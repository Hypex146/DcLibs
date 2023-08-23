package configurator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import logger.DcLogger;


public class DcConfig {
	private File config_file_ = null;
	private YamlConfiguration config_ = null;
	@SuppressWarnings("unused")
	private DcLogger logger_ = null;  // TODO
	
	public DcConfig(String path_to_config, DcLogger logger) {
		logger_ = logger;
		config_ = loadConfig(path_to_config);
	}
	
	private YamlConfiguration loadConfig(String path_to_config) {
		File config_file_ = new File(path_to_config);
		File folder = config_file_.getParentFile();
		YamlConfiguration config = new YamlConfiguration();
		
		try {
			if (!folder.exists()) {
				folder.mkdirs();
			}
			if (!config_file_.exists()) {
				config_file_.createNewFile();
			}
		} catch (SecurityException exception) {
			exception.printStackTrace();
			return null;
		} catch (IOException exception) {
			exception.printStackTrace();
			return null;
		}
		
		try {
			config.load(config_file_);
		} catch (FileNotFoundException exception) {
			exception.printStackTrace();
			return null;
		} catch (IOException exception) {
			exception.printStackTrace();
			return null;
		} catch (InvalidConfigurationException exception) {
			exception.printStackTrace();
			return null;
		} catch (IllegalArgumentException exception) {
			exception.printStackTrace();
			return null;
		}
		
		return config;
	}
	
	public YamlConfiguration getConfig() {
		return config_;
	}
	
	public void saveConfig() {
		try {
			config_.save(config_file_);
		} catch (IOException exception) {
			exception.printStackTrace();
			return;
		} catch (IllegalArgumentException exception) {
			exception.printStackTrace();
			return;
		}
	}
	
	public void reloadConfig() {
		try {
			config_.load(config_file_);
		} catch (FileNotFoundException exception) {
			exception.printStackTrace();
			return;
		} catch (IOException exception) {
			exception.printStackTrace();
			return;
		} catch (InvalidConfigurationException exception) {
			exception.printStackTrace();
			return;
		} catch (IllegalArgumentException exception) {
			exception.printStackTrace();
			return;
		}
	}
	
	// Boolean
	public Boolean getBoolean(String field, Boolean default_value) {
		if (!config_.isBoolean(field)) {
			config_.set(field, default_value);
		}
		return config_.getBoolean(field);
	}
	
	public void setBoolean(String field, Boolean value) {
		config_.set(field, value);
	}
	
	public List<Boolean> getBooleanList(String field) {
		List<Boolean> values = config_.getBooleanList(field);
		if (values.size() <= 0) {
			config_.set(field, values);
		}
		return values;
	}
	
	public void setBooleanList(String field, List<Boolean> default_values) {
		config_.set(field, default_values);
	}
	
	// Double
	public Double getDouble(String field, Double default_value) {
		if (!config_.isDouble(field)) {
			config_.set(field, default_value);
		}
		return config_.getDouble(field);
	}
	
	public void setDouble(String field, Double value) {
		config_.set(field, value);
	}
	
	public List<Double> getDoubleList(String field) {
		List<Double> values = config_.getDoubleList(field);
		if (values.size() <= 0) {
			config_.set(field, values);
		}
		return values;
	}
	
	public void setDoubleList(String field, List<Double> default_values) {
		config_.set(field, default_values);
	}
	
	// Long
	public Long getLong(String field, Long default_value) {
		if (!config_.isLong(field)) {
			config_.set(field, default_value);
		}
		return config_.getLong(field);
	}
	
	public void setLong(String field, Long value) {
		config_.set(field, value);
	}
	
	public List<Long> getLongList(String field) {
		List<Long> values = config_.getLongList(field);
		if (values.size() <= 0) {
			config_.set(field, values);
		}
		return values;
	}
	
	public void setLongList(String field, List<Long> default_values) {
		config_.set(field, default_values);
	}
	
	// Integer
	public Integer getInteger(String field, Integer default_value) {
		if (!config_.isInt(field)) {
			config_.set(field, default_value);
		}
		return config_.getInt(field);
	}
	
	public void setInteger(String field, Integer value) {
		config_.set(field, value);
	}
	
	public List<Integer> getIntegerList(String field) {
		List<Integer> values = config_.getIntegerList(field);
		if (values.size() <= 0) {
			config_.set(field, values);
		}
		return values;
	}
	
	public void setIntegerList(String field, List<Integer> default_values) {
		config_.set(field, default_values);
	}
	
	// Float
	public Float getFloat(String field, Float default_value) {
		if (!config_.isDouble(field)) {
			config_.set(field, default_value);
		}
		return (float) config_.getDouble(field);
	}
	
	public void setFloat(String field, Float value) {
		config_.set(field, value);
	}
	
	public List<Float> getFloatList(String field) {
		List<Float> values = config_.getFloatList(field);
		if (values.size() <= 0) {
			config_.set(field, values);
		}
		return values;
	}
	
	public void setFloatList(String field, List<Float> default_values) {
		config_.set(field, default_values);
	}
	
	// String
	public String getString(String field, String default_value) {
		if (!config_.isString(field)) {
			config_.set(field, default_value);
		}
		return config_.getString(field);
	}
	
	public void setString(String field, String value) {
		config_.set(field, value);
	}
	
	public List<String> getStringList(String field) {
		List<String> values = config_.getStringList(field);
		if (values.size() <= 0) {
			config_.set(field, values);
		}
		return values;
	}
	
	public void setStringList(String field, List<String> default_values) {
		config_.set(field, default_values);
	}
	
	// Vector
	public Vector getVector(String field, Vector default_value) {
		if (!config_.isVector(field)) {
			config_.set(field, default_value);
		}
		return config_.getVector(field);
	}
	
	public void setVector(String field, Vector value) {
		config_.set(field, value);
	}
	
	// Color
	public Color getColor(String field, Color default_value) {
		if (!config_.isColor(field)) {
			config_.set(field, default_value);
		}
		return config_.getColor(field);
	}
	
	public void setColor(String field, Color value) {
		config_.set(field, value);
	}
	
	// ItemStack
	public ItemStack getItemStack(String field, ItemStack default_value) {
		if (!config_.isItemStack(field)) {
			config_.set(field, default_value);
		}
		return config_.getItemStack(field);
	}
	
	public void setItemStack(String field, ItemStack value) {
		config_.set(field, value);
	}
	
	// Location
	public Location getLocation(String field, Location default_value) {
		if (!config_.isLocation(field)) {
			config_.set(field, default_value);
		}
		return config_.getLocation(field);
	}
	
	public void setLocation(String field, Location value) {
		config_.set(field, value);
	}
	
	// OfflinePlayer
	public OfflinePlayer getOfflinePlayer(String field, OfflinePlayer default_value) {
		if (!config_.isOfflinePlayer(field)) {
			config_.set(field, default_value);
		}
		return config_.getOfflinePlayer(field);
	}
	
	public void setOfflinePlayer(String field, OfflinePlayer value) {
		config_.set(field, value);
	}
	
	// Short
	public Short getShort(String field, Short default_value) {
		if (!config_.isInt(field)) {
			config_.set(field, default_value);
		}
		return (short) config_.getInt(field);
	}
	
	public void setShort(String field, Short value) {
		config_.set(field, value);
	}
	
	public List<Short> getShortList(String field) {
		List<Short> values = config_.getShortList(field);
		if (values.size() <= 0) {
			config_.set(field, values);
		}
		return values;
	}
	
	public void setShortList(String field, List<Short> default_values) {
		config_.set(field, default_values);
	}
	
}
