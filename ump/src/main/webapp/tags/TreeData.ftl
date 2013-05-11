[
<#list tree.children as child>
  {
    label: '${child.name}',
    id: '${child.id}',
    hasChildren: ${(child.hasChildren)?string}
  },
</#list>
] 