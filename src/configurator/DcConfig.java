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
	
	/**
	 * <p>
	 * The file corresponding to the configuration file
	 * </p>
	 */
	private File config_file_ = null;
	
	/**
	 * <p>
	 * YAML format configuration
	 * </p>
	 */
	private YamlConfiguration config_ = null;
	
	/**
	 * <p>
	 * Logger from DcLibs
	 * </p>
	 */
	@SuppressWarnings("unused")
	private DcLogger logger_ = null;  // TODO
	
	
	/**
	 * <p>
	 * Constructor of the DcConfig class
	 * </p>
	 * @param path_to_config - Absolute path to the configuration file
	 * @param logger - Logger from DcLibs
	 * @since 0.0.1
	 */
	public DcConfig(String path_to_config, DcLogger logger) {
		logger_ = logger;
		config_ = loadConfig(path_to_config);
	}
	
	
	/**
	 * <p>
	 * Upload a configuration file, if there is none, then create a file
	 * </p>
	 * @param path_to_config - Absolute path to the configuration file
	 * @return Loaded YAML configuration file
	 * @since 0.0.1
	 */
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
	
	
	/**
	 * <p>
	 * Get the loaded YAML configuration file
	 * </p>
	 * @return Loaded YAML configuration file
	 * @since 0.0.1
	 */
	public YamlConfiguration getConfig() {
		return config_;
	}
	
	
	/**
	 * <p>
	 * Save the configuration file to disk
	 * </p>
	 * @since 0.0.1
	 */
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
	
	
	/**
	 * <p>
	 * Reload the configuration file from disk
	 * </p>
	 * @since 0.0.1
	 */
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
	
	
	/**
	 * <p>
	 * Get the boolean value from the loaded configuration file
	 * </p>
	 * @param field - The field of the received value
	 * @param default_value - Default value for this field
	 * @return Boolean value
	 * @since 0.0.1
	 */
	public Boolean getBoolean(String field, Boolean default_value) {
		if (!config_.isBoolean(field)) {
			config_.set(field, default_value);
		}
		return config_.getBoolean(field);
	}
	
	/**
	 * <p>
	 * Set the boolean value to the loaded configuration file
	 * </p>
	 * @param field - The field of the set value
	 * @param value - Set value
	 * @since 0.0.1
	 */
	public void setBoolean(String field, Boolean value) {
		config_.set(field, value);
	}
	
	/**
	 * <p>
	 * Get the list of boolean values from the loaded configuration file
	 * </p>
	 * @param field - The field of the received list
	 * @return List of boolean values
	 * @since 0.0.1
	 */
	public List<Boolean> getBooleanList(String field) {
		List<Boolean> values = config_.getBooleanList(field);
		if (values.size() <= 0) {
			config_.set(field, values);
		}
		return values;
	}
	
	/**
	 * <p>
	 * Set the list of boolean values to the loaded configuration file
	 * </p>
	 * @param field - The field of the set values
	 * @param values - Set values
	 * @since 0.0.1
	 */
	public void setBooleanList(String field, List<Boolean> values) {
		config_.set(field, values);
	}
	
	
	/**
	 * <p>
	 * Get the double value from the loaded configuration file
	 * </p>
	 * @param field - The field of the received value
	 * @param default_value - Default value for this field
	 * @return Double value
	 * @since 0.0.1
	 */
	public Double getDouble(String field, Double default_value) {
		if (!config_.isDouble(field)) {
			config_.set(field, default_value);
		}
		return config_.getDouble(field);
	}
	
	/**
	 * <p>
	 * Set the double value to the loaded configuration file
	 * </p>
	 * @param field - The field of the set value
	 * @param value - Set value
	 * @since 0.0.1
	 */
	public void setDouble(String field, Double value) {
		config_.set(field, value);
	}
	
	/**
	 * <p>
	 * Get the list of double values from the loaded configuration file
	 * </p>
	 * @param field - The field of the received list
	 * @return List of double values
	 * @since 0.0.1
	 */
	public List<Double> getDoubleList(String field) {
		List<Double> values = config_.getDoubleList(field);
		if (values.size() <= 0) {
			config_.set(field, values);
		}
		return values;
	}
	
	/**
	 * <p>
	 * Set the list of double values to the loaded configuration file
	 * </p>
	 * @param field - The field of the set values
	 * @param values - Set values
	 * @since 0.0.1
	 */
	public void setDoubleList(String field, List<Double> values) {
		config_.set(field, values);
	}
	
	
	/**
	 * <p>
	 * Get the long value from the loaded configuration file
	 * </p>
	 * @param field - The field of the received value
	 * @param default_value - Default value for this field
	 * @return Long value
	 * @since 0.0.1
	 */
	public Long getLong(String field, Long default_value) {
		if (!config_.isLong(field)) {
			config_.set(field, default_value);
		}
		return config_.getLong(field);
	}
	
	/**
	 * <p>
	 * Set the long value to the loaded configuration file
	 * </p>
	 * @param field - The field of the set value
	 * @param value - Set value
	 * @since 0.0.1
	 */
	public void setLong(String field, Long value) {
		config_.set(field, value);
	}
	
	/**
	 * <p>
	 * Get the list of long values from the loaded configuration file
	 * </p>
	 * @param field - The field of the received list
	 * @return List of long values
	 * @since 0.0.1
	 */
	public List<Long> getLongList(String field) {
		List<Long> values = config_.getLongList(field);
		if (values.size() <= 0) {
			config_.set(field, values);
		}
		return values;
	}
	
	/**
	 * <p>
	 * Set the list of long values to the loaded configuration file
	 * </p>
	 * @param field - The field of the set values
	 * @param values - Set values
	 * @since 0.0.1
	 */
	public void setLongList(String field, List<Long> values) {
		config_.set(field, values);
	}
	
	
	/**
	 * <p>
	 * Get the integer value from the loaded configuration file
	 * </p>
	 * @param field - The field of the received value
	 * @param default_value - Default value for this field
	 * @return Integer value
	 * @since 0.0.1
	 */
	public Integer getInteger(String field, Integer default_value) {
		if (!config_.isInt(field)) {
			config_.set(field, default_value);
		}
		return config_.getInt(field);
	}
	
	/**
	 * <p>
	 * Set the integer value to the loaded configuration file
	 * </p>
	 * @param field - The field of the set value
	 * @param value - Set value
	 * @since 0.0.1
	 */
	public void setInteger(String field, Integer value) {
		config_.set(field, value);
	}
	
	/**
	 * <p>
	 * Get the list of integer values from the loaded configuration file
	 * </p>
	 * @param field - The field of the received list
	 * @return List of integer values
	 * @since 0.0.1
	 */
	public List<Integer> getIntegerList(String field) {
		List<Integer> values = config_.getIntegerList(field);
		if (values.size() <= 0) {
			config_.set(field, values);
		}
		return values;
	}
	
	/**
	 * <p>
	 * Set the list of integer values to the loaded configuration file
	 * </p>
	 * @param field - The field of the set values
	 * @param values - Set values
	 * @since 0.0.1
	 */
	public void setIntegerList(String field, List<Integer> values) {
		config_.set(field, values);
	}
	
	
	/**
	 * <p>
	 * Get the float value from the loaded configuration file
	 * </p>
	 * @param field - The field of the received value
	 * @param default_value - Default value for this field
	 * @return Float value
	 * @since 0.0.1
	 */
	public Float getFloat(String field, Float default_value) {
		if (!config_.isDouble(field)) {
			config_.set(field, default_value);
		}
		return (float) config_.getDouble(field);
	}
	
	/**
	 * <p>
	 * Set the float value to the loaded configuration file
	 * </p>
	 * @param field - The field of the set value
	 * @param value - Set value
	 * @since 0.0.1
	 */
	public void setFloat(String field, Float value) {
		config_.set(field, value);
	}
	
	/**
	 * <p>
	 * Get the list of float values from the loaded configuration file
	 * </p>
	 * @param field - The field of the received list
	 * @return List of float values
	 * @since 0.0.1
	 */
	public List<Float> getFloatList(String field) {
		List<Float> values = config_.getFloatList(field);
		if (values.size() <= 0) {
			config_.set(field, values);
		}
		return values;
	}
	
	/**
	 * <p>
	 * Set the list of float values to the loaded configuration file
	 * </p>
	 * @param field - The field of the set values
	 * @param values - Set values
	 * @since 0.0.1
	 */
	public void setFloatList(String field, List<Float> values) {
		config_.set(field, values);
	}
	
	
	/**
	 * <p>
	 * Get the string value from the loaded configuration file
	 * </p>
	 * @param field - The field of the received value
	 * @param default_value - Default value for this field
	 * @return String value
	 * @since 0.0.1
	 */
	public String getString(String field, String default_value) {
		if (!config_.isString(field)) {
			config_.set(field, default_value);
		}
		return config_.getString(field);
	}
	
	/**
	 * <p>
	 * Set the string value to the loaded configuration file
	 * </p>
	 * @param field - The field of the set value
	 * @param value - Set value
	 * @since 0.0.1
	 */
	public void setString(String field, String value) {
		config_.set(field, value);
	}
	
	/**
	 * <p>
	 * Get the list of string values from the loaded configuration file
	 * </p>
	 * @param field - The field of the received list
	 * @return List of string values
	 * @since 0.0.1
	 */
	public List<String> getStringList(String field) {
		List<String> values = config_.getStringList(field);
		if (values.size() <= 0) {
			config_.set(field, values);
		}
		return values;
	}
	
	/**
	 * <p>
	 * Set the list of string values to the loaded configuration file
	 * </p>
	 * @param field - The field of the set values
	 * @param values - Set values
	 * @since 0.0.1
	 */
	public void setStringList(String field, List<String> values) {
		config_.set(field, values);
	}
	
	
	/**
	 * <p>
	 * Get the vector value from the loaded configuration file
	 * </p>
	 * @param field - The field of the received value
	 * @param default_value - Default value for this field
	 * @return Vector value
	 * @since 0.0.1
	 */
	public Vector getVector(String field, Vector default_value) {
		if (!config_.isVector(field)) {
			config_.set(field, default_value);
		}
		return config_.getVector(field);
	}
	
	/**
	 * <p>
	 * Set the vector value to the loaded configuration file
	 * </p>
	 * @param field - The field of the set value
	 * @param value - Set value
	 * @since 0.0.1
	 */
	public void setVector(String field, Vector value) {
		config_.set(field, value);
	}
	
	
	/**
	 * <p>
	 * Get the color value from the loaded configuration file
	 * </p>
	 * @param field - The field of the received value
	 * @param default_value - Default value for this field
	 * @return Color value
	 * @since 0.0.1
	 */
	public Color getColor(String field, Color default_value) {
		if (!config_.isColor(field)) {
			config_.set(field, default_value);
		}
		return config_.getColor(field);
	}
	
	/**
	 * <p>
	 * Set the color value to the loaded configuration file
	 * </p>
	 * @param field - The field of the set value
	 * @param value - Set value
	 * @since 0.0.1
	 */
	public void setColor(String field, Color value) {
		config_.set(field, value);
	}
	
	
	/**
	 * <p>
	 * Get the item stack value from the loaded configuration file
	 * </p>
	 * @param field - The field of the received value
	 * @param default_value - Default value for this field
	 * @return Item stack value
	 * @since 0.0.1
	 */
	public ItemStack getItemStack(String field, ItemStack default_value) {
		if (!config_.isItemStack(field)) {
			config_.set(field, default_value);
		}
		return config_.getItemStack(field);
	}
	
	/**
	 * <p>
	 * Set the item stack value to the loaded configuration file
	 * </p>
	 * @param field - The field of the set value
	 * @param value - Set value
	 * @since 0.0.1
	 */
	public void setItemStack(String field, ItemStack value) {
		config_.set(field, value);
	}
	
	
	/**
	 * <p>
	 * Get the location value from the loaded configuration file
	 * </p>
	 * @param field - The field of the received value
	 * @param default_value - Default value for this field
	 * @return Location value
	 * @since 0.0.1
	 */
	public Location getLocation(String field, Location default_value) {
		if (!config_.isLocation(field)) {
			config_.set(field, default_value);
		}
		return config_.getLocation(field);
	}
	
	/**
	 * <p>
	 * Set the location value to the loaded configuration file
	 * </p>
	 * @param field - The field of the set value
	 * @param value - Set value
	 * @since 0.0.1
	 */
	public void setLocation(String field, Location value) {
		config_.set(field, value);
	}
	
	
	/**
	 * <p>
	 * Get the offline player value from the loaded configuration file
	 * </p>
	 * @param field - The field of the received value
	 * @param default_value - Default value for this field
	 * @return Offline player value
	 * @since 0.0.1
	 */
	public OfflinePlayer getOfflinePlayer(
			String field, OfflinePlayer default_value) {
		if (!config_.isOfflinePlayer(field)) {
			config_.set(field, default_value);
		}
		return config_.getOfflinePlayer(field);
	}
	
	/**
	 * <p>
	 * Set the offline player value to the loaded configuration file
	 * </p>
	 * @param field - The field of the set value
	 * @param value - Set value
	 * @since 0.0.1
	 */
	public void setOfflinePlayer(String field, OfflinePlayer value) {
		config_.set(field, value);
	}
	
	
	/**
	 * <p>
	 * Get the short value from the loaded configuration file
	 * </p>
	 * @param field - The field of the received value
	 * @param default_value - Default value for this field
	 * @return Short value
	 * @since 0.0.1
	 */
	public Short getShort(String field, Short default_value) {
		if (!config_.isInt(field)) {
			config_.set(field, default_value);
		}
		return (short) config_.getInt(field);
	}
	
	/**
	 * <p>
	 * Set the short player value to the loaded configuration file
	 * </p>
	 * @param field - The field of the set value
	 * @param value - Set value
	 * @since 0.0.1
	 */
	public void setShort(String field, Short value) {
		config_.set(field, value);
	}
	
	/**
	 * <p>
	 * Get the list of short values from the loaded configuration file
	 * </p>
	 * @param field - The field of the received list
	 * @return List of short values
	 * @since 0.0.1
	 */
	public List<Short> getShortList(String field) {
		List<Short> values = config_.getShortList(field);
		if (values.size() <= 0) {
			config_.set(field, values);
		}
		return values;
	}
	
	/**
	 * <p>
	 * Set the list of short values to the loaded configuration file
	 * </p>
	 * @param field - The field of the set values
	 * @param values - Set values
	 * @since 0.0.1
	 */
	public void setShortList(String field, List<Short> values) {
		config_.set(field, values);
	}
	
}
