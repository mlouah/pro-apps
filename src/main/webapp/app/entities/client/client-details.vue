<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="client">
        <h2 class="jh-entity-heading" data-cy="clientDetailsHeading">
          <span v-text="$t('proAppsApp.client.detail.title')">Client</span> {{ client.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('proAppsApp.client.name')">Name</span>
          </dt>
          <dd>
            <span>{{ client.name }}</span>
          </dd>
          <dt>
            <span v-text="$t('proAppsApp.client.code')">Code</span>
          </dt>
          <dd>
            <span>{{ client.code }}</span>
          </dd>
          <dt>
            <span v-text="$t('proAppsApp.client.clientLogo')">Client Logo</span>
          </dt>
          <dd>
            <div v-if="client.clientLogo">
              <a v-on:click="openFile(client.clientLogoContentType, client.clientLogo)">
                <img
                  v-bind:src="'data:' + client.clientLogoContentType + ';base64,' + client.clientLogo"
                  style="max-width: 100%"
                  alt="client image"
                />
              </a>
              {{ client.clientLogoContentType }}, {{ byteSize(client.clientLogo) }}
            </div>
          </dd>
          <dt>
            <span v-text="$t('proAppsApp.client.url')">Url</span>
          </dt>
          <dd>
            <span>{{ client.url }}</span>
          </dd>
          <dt>
            <span v-text="$t('proAppsApp.client.dateCreation')">Date Creation</span>
          </dt>
          <dd>
            <span v-if="client.dateCreation">{{ $d(Date.parse(client.dateCreation), 'long') }}</span>
          </dd>
          <dt>
            <span v-text="$t('proAppsApp.client.dateModify')">Date Modify</span>
          </dt>
          <dd>
            <span v-if="client.dateModify">{{ $d(Date.parse(client.dateModify), 'long') }}</span>
          </dd>
          <dt>
            <span v-text="$t('proAppsApp.client.lastModifyBy')">Last Modify By</span>
          </dt>
          <dd>
            <span>{{ client.lastModifyBy }}</span>
          </dd>
          <dt>
            <span v-text="$t('proAppsApp.client.createdBy')">Created By</span>
          </dt>
          <dd>
            <span>{{ client.createdBy }}</span>
          </dd>
          <dt>
            <span v-text="$t('proAppsApp.client.notes')">Notes</span>
          </dt>
          <dd>
            <span>{{ client.notes }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link v-if="client.id" :to="{ name: 'ClientEdit', params: { clientId: client.id } }" custom v-slot="{ navigate }">
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./client-details.component.ts"></script>
