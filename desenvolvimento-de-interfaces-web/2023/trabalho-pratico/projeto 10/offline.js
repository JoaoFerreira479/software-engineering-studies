(function () {
    "use strict";

    const STORAGE_KEY = "portalOfflineMode";
    const EVENT_NAME = "portalOfflineChange";

    function isOfflineMode() {
        try {
            return localStorage.getItem(STORAGE_KEY) === "1";
        } catch (e) {
            return false;
        }
    }

    function setOfflineMode(value) {
        try {
            localStorage.setItem(STORAGE_KEY, value ? "1" : "0");
            window.dispatchEvent(new CustomEvent(EVENT_NAME, { detail: { offline: !!value } }));
            return true;
        } catch (e) {
            return false;
        }
    }

    function toggleOfflineMode() {
        const next = !isOfflineMode();
        setOfflineMode(next);
        return next;
    }

    window.PortalOffline = {
        isOfflineMode: isOfflineMode,
        setOfflineMode: setOfflineMode,
        toggleOfflineMode: toggleOfflineMode,
        EVENT_NAME: EVENT_NAME
    };
})();
