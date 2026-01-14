#!/usr/bin/env bash

# ============================================================
# Captura saída do Jest e gera imagens contínuas do terminal
# Dark + Light
# Todos os arquivos ficam em ./capture-jest-terminal
# ============================================================

BASE_DIR="./capture-jest-terminal"
DPI=200

mkdir -p "$BASE_DIR"

ANSI_FILE="$BASE_DIR/output.ansi"
HTML_RAW="$BASE_DIR/output_raw.html"
HTML_BASE="$BASE_DIR/output_base.html"

HTML_DARK="$BASE_DIR/output_dark.html"
HTML_LIGHT="$BASE_DIR/output_light.html"

PDF_DARK="$BASE_DIR/output_dark.pdf"
PDF_LIGHT="$BASE_DIR/output_light.pdf"

OUT_DARK="$BASE_DIR/terminal_capture-dark"
OUT_LIGHT="$BASE_DIR/terminal_capture-light"

echo "▶ Executando Jest..."
FORCE_COLOR=3 npx jest --verbose --runInBand --coverage > "$ANSI_FILE" 2>&1
echo "✔ Jest finalizado"

echo "▶ Convertendo ANSI para HTML..."
ansifilter --html --input "$ANSI_FILE" --encoding=UTF-8 --output "$HTML_RAW"
echo "✔ ANSI convertido"

cp "$HTML_RAW" "$HTML_BASE"

echo "▶ Corrigindo token PASS..."
sed -E -i \
  's/<span class="ansi-green">PASS<\/span>/<span class="pass-token">PASS<\/span>/g' \
  "$HTML_BASE"
echo "✔ PASS corrigido"

# ------------------------------------------------------------
# CSS DARK
# ------------------------------------------------------------
cat > "$HTML_DARK" <<EOF
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
@page { margin: 0; size: auto; }
body {
  margin: 0;
  background: #0c0c0c;
  color: #d4d4d4;
  font-family: "JetBrains Mono","Fira Code","DejaVu Sans Mono",monospace;
  font-size: 14px;
  line-height: 1.35;
  font-weight: 500;
}
pre { margin: 0; }
.pass-token {
  background: #00ff00;
  color: #000;
  font-weight: 700;
  padding: 0 4px;
}
</style>
</head>
<body>
$(cat "$HTML_BASE")
</body>
</html>
EOF

# ------------------------------------------------------------
# CSS LIGHT
# ------------------------------------------------------------
cat > "$HTML_LIGHT" <<EOF
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
@page { margin: 0; size: auto; }
body {
  margin: 0;
  background: #ffffff;
  color: #000000;
  font-family: "JetBrains Mono","Fira Code","DejaVu Sans Mono",monospace;
  font-size: 14px;
  line-height: 1.35;
  font-weight: 500;
}
pre { margin: 0; }
.pass-token {
  background: #00c800;
  color: #000;
  font-weight: 700;
  padding: 0 4px;
}
</style>
</head>
<body>
$(cat "$HTML_BASE")
</body>
</html>
EOF

echo "▶ Gerando PDF DARK..."
weasyprint "$HTML_DARK" "$PDF_DARK" && echo "✔ PDF DARK OK"

echo "▶ Gerando PDF LIGHT..."
weasyprint "$HTML_LIGHT" "$PDF_LIGHT" && echo "✔ PDF LIGHT OK"

echo "▶ Convertendo PDFs para JPG..."
pdftoppm -jpeg -r "$DPI" "$PDF_DARK" "$OUT_DARK"
pdftoppm -jpeg -r "$DPI" "$PDF_LIGHT" "$OUT_LIGHT"

echo
echo "✅ Processo finalizado"
echo "📁 Pasta: $BASE_DIR"
ls -lh "$BASE_DIR"
