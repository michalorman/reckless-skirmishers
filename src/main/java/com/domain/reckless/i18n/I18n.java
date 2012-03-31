package com.domain.reckless.i18n;

/**
 * Interface for components performing internationalization of messages.
 */
public interface I18n {

    /**
     * Returns localized message assigned to given key.
     *
     * @param key Key of the message to return.
     * @return Localized message.
     */
    public String t(String key);

}
