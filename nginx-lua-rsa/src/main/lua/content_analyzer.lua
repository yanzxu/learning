local resty_rsa = require "resty.rsa"

local base64_decoder = require("base64.decoder")

local rsa_priv_key = [[-----BEGIN RSA PRIVATE KEY-----
MIICXAIBAAKBgQDXoBMCKCWDrQ875BNdp3E2ODjMO5jkdzCCY8UxTihBKUiiSL0K
X3VZ2B9oQPrAAPho+Oyj1/jNer5nQWHzbWDedIlvXWyChjQQrlNWRBY4W4kgIrSD
GIMyzzBzZN1fbaFxvVEUOAkYExMOL/ayY2Nnm8DGgnQuvEoBD6dditTHgQIDAQAB
AoGBANDsXP6d/5tSN4yENO9tqhgq6X2Z0U6s3ee42/wmCoL4iVQUvnYqf/4JloTh
URRw3EB50HAAdyZBY/OQOmQu+sdIj6Gd3yl0bULQ++F36dMlLX0HanqP7irb6eKL
Aku03KiCPm5271J2G6miEJiRSLV288TwyMUI+aNIawZCK8ABAkEA+DeDgh0x3Ost
wER/+Pbg4WeLsLwMegHq2RuFN6tAD6yenpTNd56c6muUWUm5Pqk+IYAeDcteVyYW
mlMVYV+uwQJBAN5i8QsOQ/znMsK3cMPcSy1N7v0sCtsJ/GcS0vfpgyyTvaDonrae
ycRoEdGq6iODOdCFiFScNlpG1/xb4e88CMECQFVUsE8Qvk2WQ/ovEleCd7jFog41
94wSEKxme5uxsaoxy630tdYDnyovpaY0ZAyYnhViN/LCEUKgg3C2U0lQXYECQDq4
k39g1/D6t3uU5pDBJZSj5HwzWfZ2j5BD9zMBFOL7a/IGGoqMKSjUdggQAl04NFbs
O5Bj4zI5TKTVOjZqesECQEUo9rZh0BlvdQhGtBlI+WmA5PY+nUxzyyM5hTuXs/SV
1jq56C2YOG2Sl53sq2kAEdMGc78XP+lpXBR+4VMf5f4=
-----END RSA PRIVATE KEY-----]]

local priv, err = resty_rsa:new({ private_key = rsa_priv_key })

if not priv then
    ngx.say("new rsa err: ", err)
    return
end

local decoded_path = base64_decoder.dec(ngx.var.path)

local decrypted_path = priv:decrypt(decoded_path)

local host = [[https://github.com/yanzxu/]]
local full_path = host .. decrypted_path

ngx.redirect(full_path, 302)



