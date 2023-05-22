<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="person">
        <h2 class="jh-entity-heading" data-cy="personDetailsHeading">
          <span v-text="$t('proAppsApp.person.detail.title')">Person</span> {{ person.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('proAppsApp.person.name')">Name</span>
          </dt>
          <dd>
            <span>{{ person.name }}</span>
          </dd>
          <dt>
            <span v-text="$t('proAppsApp.person.code')">Code</span>
          </dt>
          <dd>
            <span>{{ person.code }}</span>
          </dd>
          <dt>
            <span v-text="$t('proAppsApp.person.isExternal')">Is External</span>
          </dt>
          <dd>
            <span>{{ person.isExternal }}</span>
          </dd>
          <dt>
            <span v-text="$t('proAppsApp.person.dateCreation')">Date Creation</span>
          </dt>
          <dd>
            <span v-if="person.dateCreation">{{ $d(Date.parse(person.dateCreation), 'long') }}</span>
          </dd>
          <dt>
            <span v-text="$t('proAppsApp.person.dateModify')">Date Modify</span>
          </dt>
          <dd>
            <span v-if="person.dateModify">{{ $d(Date.parse(person.dateModify), 'long') }}</span>
          </dd>
          <dt>
            <span v-text="$t('proAppsApp.person.lastModifyBy')">Last Modify By</span>
          </dt>
          <dd>
            <span>{{ person.lastModifyBy }}</span>
          </dd>
          <dt>
            <span v-text="$t('proAppsApp.person.createdBy')">Created By</span>
          </dt>
          <dd>
            <span>{{ person.createdBy }}</span>
          </dd>
          <dt>
            <span v-text="$t('proAppsApp.person.notes')">Notes</span>
          </dt>
          <dd>
            <span>{{ person.notes }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link v-if="person.id" :to="{ name: 'PersonEdit', params: { personId: person.id } }" custom v-slot="{ navigate }">
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./person-details.component.ts"></script>
