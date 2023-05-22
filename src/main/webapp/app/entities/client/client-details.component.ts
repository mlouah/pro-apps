import { Component, Inject } from 'vue-property-decorator';

import { mixins } from 'vue-class-component';
import JhiDataUtils from '@/shared/data/data-utils.service';

import { IClient } from '@/shared/model/client.model';
import ClientService from './client.service';
import AlertService from '@/shared/alert/alert.service';

@Component
export default class ClientDetails extends mixins(JhiDataUtils) {
  @Inject('clientService') private clientService: () => ClientService;
  @Inject('alertService') private alertService: () => AlertService;

  public client: IClient = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.clientId) {
        vm.retrieveClient(to.params.clientId);
      }
    });
  }

  public retrieveClient(clientId) {
    this.clientService()
      .find(clientId)
      .then(res => {
        this.client = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
