

#### if decrypt uri failed will return 403
```
if ($secure_link = "") {
	return 403;
}
```

#### if uri has expired will return 410
```
if ($secure_link = "0") {
	return 403;
}
```