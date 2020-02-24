package constant

// AppTag returns commit tag or dev
func AppTag() string {
	if appTag == "" || appTag == "SED_APP_TAG" {
		return "dev"
	}

	return appTag
}
