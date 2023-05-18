<template>
  <div>
    <b-dropdown-item to="/mo-m">
      <font-awesome-icon icon="asterisk" />
      <span v-text="$t('global.menu.entities.moM')">Mo M</span>
    </b-dropdown-item>
    <b-dropdown-item to="/task">
      <font-awesome-icon icon="asterisk" />
      <span v-text="$t('global.menu.entities.task')">Task</span>
    </b-dropdown-item>
    <b-dropdown-item to="/person">
      <font-awesome-icon icon="asterisk" />
      <span v-text="$t('global.menu.entities.person')">Person</span>
    </b-dropdown-item>
    <b-dropdown-item to="/project">
      <font-awesome-icon icon="asterisk" />
      <span v-text="$t('global.menu.entities.project')">Project</span>
    </b-dropdown-item>
    <b-dropdown-item to="/task-status">
      <font-awesome-icon icon="asterisk" />
      <span v-text="$t('global.menu.entities.taskStatus')">Task Status</span>
    </b-dropdown-item>
    <b-dropdown-item to="/task-priority">
      <font-awesome-icon icon="asterisk" />
      <span v-text="$t('global.menu.entities.taskPriority')">Task Priority</span>
    </b-dropdown-item>
    <!-- jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here -->
  </div>
</template>

<script lang="ts" src="./entities-menu.component.ts"></script>
