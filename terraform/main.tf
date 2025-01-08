provider "azurerm" {
  features {}
}

resource "azurerm_resource_group" "rg" {
  name     = "eventhub-kafka-rg"
  location = "australiaeast"  # Change to your preferred region
}

resource "azurerm_eventhub_namespace" "namespace" {
  name                = "eventhub-kafka-namespace"
  location            = azurerm_resource_group.rg.location
  resource_group_name = azurerm_resource_group.rg.name
  sku                 = "Standard"   # Free tier is available for Basic SKU
  capacity            = 1         # Capacity for the namespace
}

resource "azurerm_eventhub" "eventhub" {
  name                = "kafka-eventhub"
  namespace_name      = azurerm_eventhub_namespace.namespace.name
  resource_group_name = azurerm_resource_group.rg.name
  partition_count     = 2         # Number of partitions
  message_retention   = 1         # Retention period in days
}

resource "azurerm_eventhub_consumer_group" "consumer_group" {
  name                = "my-consumer-group"
  eventhub_name       = azurerm_eventhub.eventhub.name
  namespace_name      = azurerm_eventhub_namespace.namespace.name
  resource_group_name = azurerm_resource_group.rg.name
}

resource "azurerm_eventhub_authorization_rule" "send" {
  name                = "send-rule"
  namespace_name      = azurerm_eventhub_namespace.namespace.name
  eventhub_name       = azurerm_eventhub.eventhub.name
  resource_group_name = azurerm_resource_group.rg.name
  listen              = false
  send                = true
  manage              = false
}

resource "azurerm_eventhub_authorization_rule" "listen" {
  name                = "listen-rule"
  namespace_name      = azurerm_eventhub_namespace.namespace.name
  eventhub_name       = azurerm_eventhub.eventhub.name
  resource_group_name = azurerm_resource_group.rg.name
  listen              = true
  send                = false
  manage              = false
}
