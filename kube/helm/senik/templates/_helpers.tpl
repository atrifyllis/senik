{{/*
Expand the name of the chart.
*/}}
{{- define "senik.name" -}}
{{- default .Chart.Name .Values.nameOverride | trunc 63 | trimSuffix "-" }}
{{- end }}

{{/*
Create a default fully qualified app name.
We truncate at 63 chars because some Kubernetes name fields are limited to this (by the DNS naming spec).
If release name contains chart name it will be used as a full name.
*/}}
{{- define "senik.fullname" -}}
{{- if .Values.fullnameOverride }}
{{- .Values.fullnameOverride | trunc 63 | trimSuffix "-" }}
{{- else }}

{{- /* Could not use name here because I want to use this template in subchart but when using it there the child scope is used so the name is different.
I could not find a way to pass the parent scope}}
{{- $name := default .Chart.Name .Values.nameOverride }}}}
{{- printf "%s-%s" .Release.Name $name | trunc 63 | trimSuffix "-" }}}}*/ -}}
{{- .Release.Name | trunc 63 | trimSuffix "-" }}
{{- end }}
{{- end }}

{{/*
Create chart name and version as used by the chart label.
*/}}
{{- define "senik.chart" -}}
{{- printf "%s-%s" .Chart.Name .Chart.Version | replace "+" "_" | trunc 63 | trimSuffix "-" }}
{{- end }}

{{/*
Common labels
*/}}
{{- define "senik.labels" -}}
helm.sh/chart: {{ include "senik.chart" . }}
{{ include "senik.selectorLabels" . }}
{{- if .Chart.AppVersion }}
app.kubernetes.io/version: {{ .Chart.AppVersion | quote }}
{{- end }}
app.kubernetes.io/managed-by: {{ .Release.Service }}
{{- end }}

{{/*
Selector labels
*/}}
{{- define "senik.selectorLabels" -}}
app.kubernetes.io/name: {{ include "senik.name" . }}
app.kubernetes.io/instance: {{ .Release.Name }}
{{- end }}

{{/*
Create the name of the service account to use
*/}}
{{- define "senik.serviceAccountName" -}}
{{- if .Values.serviceAccount.create }}
{{- default (include "senik.fullname" .) .Values.serviceAccount.name }}
{{- else }}
{{- default "default" .Values.serviceAccount.name }}
{{- end }}
{{- end }}



{{/*
NOTE: This utility template is needed until https://git.io/JvuGN is resolved.

Call a template from the context of a subchart.

Usage:
  {{ include "call-nested" (list . "<subchart_name>" "<subchart_template_name>") }}
*/}}
{{- define "call-nested" }}
{{- $dot := index . 0 }}
{{- $subchart := index . 1 | splitList "." }}
{{- $template := index . 2 }}
{{- $values := $dot.Values }}
{{- range $subchart }}
{{- $values = index $values . }}
{{- end }}
{{- include $template (dict "Chart" (dict "Name" (last $subchart)) "Values" $values "Release" $dot.Release "Capabilities" $dot.Capabilities) }}
{{- end }}
