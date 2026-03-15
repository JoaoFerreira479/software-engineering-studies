$root = $PSScriptRoot
$pkgs = @('programarcomputadoresalternativasdecisao','programarcomputadoresrepeticao','programarcomputadoresvariaveisdados','programarcomputadoresideiasedesafios','programarcomputadoresarquivos','desafioemsala','auladenivelamento','algoritmoknn')
$importBlock = "`nimport lab.listas.infrastructure.io.Console;`nimport lab.listas.infrastructure.io.EntradaUtil;"

foreach ($pkg in $pkgs) {
  $dir = Join-Path $root "src\$pkg"
  if (-not (Test-Path $dir)) { continue }
  Get-ChildItem $dir -Filter *.java | Where-Object { $_.Name -ne 'EntradaUtil.java' } | ForEach-Object {
    $content = Get-Content $_.FullName -Raw -Encoding UTF8
    $needsImport = ($content -match 'System\.out\.|EntradaUtil\.') -and ($content -notmatch 'lab\.listas\.infrastructure\.io')
    $content = $content -replace 'System\.out\.', 'Console.'
    if ($needsImport) {
      $pattern = "package $pkg;"
      $replacement = "package ${pkg};$importBlock"
      $content = $content.Replace($pattern, $replacement)
    }
    Set-Content $_.FullName $content -NoNewline -Encoding UTF8
  }
}
Write-Host "Migration done."
